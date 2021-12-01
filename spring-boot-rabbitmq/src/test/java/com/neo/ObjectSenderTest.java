package com.neo;

import com.neo.configtest.object.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 发送对象测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectSenderTest {
    @Autowired
    private ObjectSender objectSender;

    @Test
    public void objectSenderTest() {
        for (int i = 0; i < 3; i++) {
            objectSender.send(i);
        }
    }
}