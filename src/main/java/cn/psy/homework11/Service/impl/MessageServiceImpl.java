package cn.psy.homework11.Service.impl;

import cn.psy.homework11.Service.MessageService;
import cn.psy.homework11.dao.MessageDAO;
import cn.psy.homework11.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Message的接口实现类
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDAO messageDAO;

    /**
     * 查询找所有文章
     * @return List
     */
    @Override
    public List<Message> queryAllMessages() {
        return messageDAO.queryAllMessages();
    }

    /**
     * 根据文章的id查找文章信息
     * @param id
     * @return List
     */
    @Override
    public Message queryMessageById(int id) {

        return messageDAO.queryMessageById(id);
    }

    /**
     * 增加文章
     * @return int
     */
    @Override
    public int addMessage(String title,String article,int user_id,String author,Date time,int reply_count) {
        return messageDAO.addMessage(title,article,user_id,author,time,reply_count);
    }

    /**
     * 删除文章
     * @param id
     * @return int
     */
    @Override
    public int deleteMessageById(int id) {
        return messageDAO.deleteMessageById(id);
    }

    /**
     * 修改文章
     * @param title
     * @param article
     * @param author
     * @param time
     * @return int
     */
    @Override
    public int updateMessageById(int id,String title, String article,int user_id, String author, Date time,int reply_count) {
         return messageDAO.updateMessageById(id,title,article,user_id,author,time,reply_count);
    }

    /**
     * 根据作者查找文章
     * @param author
     * @return Message
     */
    @Override
    public List<Message> queryMessagesByAuthor(String author){
        return messageDAO.queryMessagesByAuthor(author);
    }

}
