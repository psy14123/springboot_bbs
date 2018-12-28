package cn.psy.homework11.Service.impl;

import cn.psy.homework11.Service.MessageService;
import cn.psy.homework11.pojo.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MessageServiceImplTest {

    @Autowired
    MessageService messageService;


    /**
     * 删除文章的测试
     */

    @Test
    public void deleteMessageById() {
        Message old = messageService.queryMessageById(34);
        messageService.deleteMessageById(34);
        Message new1 = messageService.queryMessageById(34);
        assertEquals(old.getTitle(),null);
        assertEquals(new1.getTitle(),null);
    }

    /**
     * 更改文章的测试
     */
    @Test
    public void updateMessageById() {
        Message old = messageService.queryMessageById(34);
        Date time = new Date();
        messageService.updateMessageById(34,"ad","ad",37,"彭思远",time,3);

        Message new1 = messageService.queryMessageById(34);
        assertEquals(old.getArticle(),new1.getArticle());
        assertEquals(old.getTitle(),old.getTitle());
    }


}