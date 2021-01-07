package com.neo;

import com.neo.configtest.many.NeoSender1;
import com.neo.configtest.many.NeoSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 一对多，多对多测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {
    @Autowired
    private NeoSender1 neoSender1;

    @Autowired
    private NeoSender2 neoSender2;

    @Test
    public void manyToMany() {
        for (int i = 0; i < 10; i++) {
            neoSender1.send(i);
            neoSender2.send(i);
        }
    }
}