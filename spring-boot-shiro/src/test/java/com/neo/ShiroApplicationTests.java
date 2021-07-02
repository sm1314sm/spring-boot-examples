package com.neo;

import com.neo.constants.Constants;
import net.sf.ehcache.Element;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {
	@Autowired
	private CacheManager cacheManager;

	@Test
	public void contextLoads() {
		Cache cache = cacheManager.getCache(Constants.LOGINRECORDCACHE);
		System.out.println(cache.keys());
	}
}
