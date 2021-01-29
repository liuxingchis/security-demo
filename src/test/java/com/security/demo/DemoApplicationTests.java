package com.security.demo;

import com.security.demo.reposittory.SysUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    SysUserRepository sysUserRepository;

    @Test
    void contextLoads() {
    }

}
