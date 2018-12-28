package cn.psy.homework11.dao;

import cn.psy.homework11.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * message的相关接口
 */
@Repository
@Mapper
public interface MessageDAO {
    /**
     * 查询所有信息，在前台页面显示用
     * @return Message
     */
    List<Message> queryAllMessages();

    /**
     *根据id查找信息
     * @param id
     * @return List
     */
    Message queryMessageById(@Param("id") int id);

    /**
     * 增加信息
     * @return int
     */
    int addMessage(@Param("title") String title,@Param("article") String article,@Param("user_id") int user_id,
                   @Param("author") String author,@Param("time") Date time,@Param("reply_count") int reply_count);

    /**
     * 根据文章的编号来删除
     * @param id
     * @return int
     */
    int deleteMessageById(@Param("id") int id);

    /**
     * 根据文章的id来修改文章
     * @param title
     * @param article
     * @param author
     * @param time
     * @return int
     */
    int updateMessageById(@Param("id") int id,@Param("title") String title,@Param("article") String article,@Param("user_id") int user_id,
                          @Param("author") String author,@Param("time") Date time,@Param("reply_count") int reply_count);


    /**
     * 根据作者查找文章
     * @param author
     * @return Message
     */
    List<Message> queryMessagesByAuthor(@Param("author") String author);
}
