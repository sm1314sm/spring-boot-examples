package com.neo;

import com.neo.controller.HelloController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloTests {
    private MockMvc mvc;

    /**
     * 在执行测试代码前获取MockMvc
     */
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    /**
     * 1、mockMvc.perform执行一个请求。
     * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
     * 3、ResultActions.param添加请求传值
     * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
     * 5、ResultActions.andExpect添加执行完成后的断言。
     * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
     * 7、ResultActions.andReturn表示执行完成后返回相应的结果。
     */
    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/direct").param("name", "tom").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello World! -by tom"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 提取HTML中的纯文本
     */
    @Test
    public void testHtml() {
        String html = "<html><head><title>这是文本信息</title></head><body><p>文本信息二</p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.text());
        System.out.println(html.replaceAll("<[.[^<]]*>", ""));
    }
}