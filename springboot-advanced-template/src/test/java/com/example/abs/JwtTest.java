package com.example.abs;

import com.example.abs.common.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTest {
    @Test
    void jwt() {
        String token = JwtUtils.generateToken("张三");
        System.out.println(token);

        String subject = null;
        try {
            subject = JwtUtils.decodeByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(subject);
    }
}
