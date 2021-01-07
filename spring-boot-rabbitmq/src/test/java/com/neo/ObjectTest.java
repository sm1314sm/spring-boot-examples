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
public class ObjectTest {
	@Autowired
	private ObjectSender objectSender;

	@Test
	public void objectSender() {
		objectSender.send();
	}
}