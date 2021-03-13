package com.laiteam.developerforfun;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DeveloperforfunApplication.class)
class DeveloperforfunApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void testJasypt() {
        System.out.println("加密 = " + encryptor.encrypt("test"));
    }

    void contextLoads() {
        System.out.println("test");
    }

}
