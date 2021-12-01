package com.neo;

import com.neo.configtest.string.StringSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 发送字符串测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringSenderTest {
    @Autowired
    private StringSender stringSender;

    @Test
    public void stringSenderTest() {
        for (int i = 0; i < 3; i++) {
            stringSender.send(i);
        }
    }
}