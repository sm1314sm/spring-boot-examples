package com.neo;

import com.neo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    public void testSimpleMail() {
        mailService.sendSimpleMail("ghx19960610@163.com", "configtest simple mail", " direct this is simple mail");
    }

    @Test
    public void testHtmlMail() {
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>direct world ! 这是一封html邮件!</h3>\n" +
                "<a href='https://www.baidu.com'>百度</a>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("1731571327@qq.com", "configtest simple mail", content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath = "d:\\data\\pup.js";
        mailService.sendAttachmentsMail("1731571327@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = UUID.randomUUID().toString();
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "d:\\data\\image\\configtest.jpg";
        mailService.sendInlineResourceMail("1731571327@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }
}
