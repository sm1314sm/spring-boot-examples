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
		Cache cache1 = ehCacheManager.getCache(Constants.SYS_AUTH_CACHE_1);
		Cache cache2 = ehCacheManager.getCache(Constants.SYS_AUTH_CACHE_2);
		Cache cache3 = ehCacheManager.getCache(Constants.LOGINRECORDCACHE);
		System.out.println(cache1.keys());
		System.out.println(cache1.values());
		System.out.println(cache2.keys());
		System.out.println(cache2.values());
		System.out.println(cache3.keys());
		System.out.println(cache3.values());
	}
}
