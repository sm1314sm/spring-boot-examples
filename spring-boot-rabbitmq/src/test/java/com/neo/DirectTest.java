package com.neo;

import com.neo.direct.DirectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectTest {
    @Autowired
    private DirectSender directSender;

    @Test
    public void directSendA() {
        directSender.directSendA();
    }

    @Test
    public void directSendB() {
        directSender.directSendB();
    }
}