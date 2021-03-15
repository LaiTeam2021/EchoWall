package com.laiteam.echowall;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//TODO can't run
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EchoWallApplication.class)
public class EchoWallApplicationTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void testJasypt() {
        System.out.println("加密 = " + encryptor.encrypt("test"));
    }

}
