package cn.psy.homework11.Service;

import cn.psy.homework11.pojo.Message;

import java.util.Date;
import java.util.List;

/**
 * message相关接口
 */
public interface MessageService {
    /**
     * 用来查询所有文章
     * @return
     */
    List<Message> queryAllMessages();

    /**
     * 根据文章的id来查询对应的文章
     * @param id
     * @return Message
     */
    Message queryMessageById(int id);

    /**
     * 增加信息
     * @return int
     */
    int addMessage(String title,String article,int user_id,String author,Date time,int reply_count);

    /**
     * 根据文章的id来删除
     * @param id
     * @return int
     */
    int deleteMessageById(int id);

    /**
     * 根据文章的id来修改文章
     * @param title
     * @param article
     * @param author
     * @param time
     * @return int
     */
    int updateMessageById(int id,String title, String article,int user_id, String author, Date time,int reply_count);

    /**
     * 根据作者来查找文章
     * @param Author
     * @return
     */
    List<Message> queryMessagesByAuthor(String Author);
}
