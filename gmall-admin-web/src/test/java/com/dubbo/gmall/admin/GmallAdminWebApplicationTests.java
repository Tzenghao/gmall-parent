package com.dubbo.gmall.admin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

/*@SpringBootTest*/
class GmallAdminWebApplicationTests {

    @Test
    void contextLoads() {
        //spring自带的MD5加密
        String asHex = DigestUtils.md5DigestAsHex("'123456".getBytes());
        System.out.println(asHex);
    }

}
