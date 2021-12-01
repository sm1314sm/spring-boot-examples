package com.neo;

import com.neo.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {
    @Autowired
    private TopicSender topicSender;

    @Test
    public void topicSendA() {
        topicSender.topicSendA();
    }

    @Test
    public void topicSendB() {
        topicSender.topicSendB();
    }
}