package com.security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomizeAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomizeLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private CustomizeAccessDecisionManager accessDecisionManager;

    @Autowired
    private CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    private CustomizeAbstractSecurityInterceptor securityInterceptor;

    @Autowired
    private CustomizeAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//登入
                .permitAll()//允许所有用户
                .successHandler(authenticationSuccessHandler)//登录成功处理逻辑
                .failureHandler(authenticationFailureHandler)//登录失败处理逻辑
                .and().logout()
                .permitAll()
                .logoutSuccessHandler(logoutSuccessHandler)
                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)//未登录
                .accessDeniedHandler(accessDeniedHandler)//权限不足
                .and()// 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 允许匿名访问
                .antMatchers("/no-auth/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated().withObjectPostProcessor(
                new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager);//决策管理器
                        o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源
                        return o;
                    }
                });

        // 关闭CSRF跨域
        http.csrf().disable();
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
    }
}
