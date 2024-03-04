package com.java.wiki;

import com.java.wiki.req.UserSaveReq;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class WikiApplicationTests {

    @Test
    void contextLoads() {


    }

    public static void main(String[] args) {
        UserSaveReq req = new UserSaveReq();
        req.setPassword("32adf1cd66bd533cf22de3b04505ed85");
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        System.out.println(req.getPassword());
    }

}
