package com.neo;

import com.neo.constants.Constants;
import net.sf.ehcache.Element;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {
	@Autowired
	private EhCacheManager ehCacheManager;

	@Test
	public void contextLoads() {
		Cache cache = ehCacheManager.getCache(Constants.SYS_AUTH_CACHE);
		cache.put("key1", "value1");
		System.out.println(cache.get("key1"));
		System.out.println(cache.keys());
		System.out.println(cache.values());
	}
}
