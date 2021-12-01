package com.neo;

import com.neo.headers.HeadersSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadersTest {
    @Autowired
    private HeadersSender headersSender;

    @Test
    public void headersSendA() {
        headersSender.headersSendA();
    }

    @Test
    public void headersSendB() {
        headersSender.headersSendB();
    }
}
