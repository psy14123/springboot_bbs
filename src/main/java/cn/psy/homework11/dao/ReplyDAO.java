package cn.psy.homework11.dao;

import cn.psy.homework11.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 评论相关的接口
 */
@Repository
@Mapper
public interface ReplyDAO {
    /**
     * 根据文章的id查找评论
     * @return Reply
     */
    List<Reply> queryReplyByMessage(@Param("message_id") int meesage_id);

    /**
     * 增加评论
     * @param message_id
     * @param replier
     * @param reply_content
     * @param reply_time
     * @return int
     */
    int addReplyByMessage(@Param("message_id") int message_id, @Param("replier") String replier,
                          @Param("reply_content") String reply_content, @Param("reply_time") Date reply_time);

    /**
     * 根据文章的id查找评论
     * @param message_id
     * @return Reply
     */
    Reply queryReplyById(@Param("message_id") String message_id);


    /**
     * 根据文章的id查找最新评论时间
     * @param message_id
     * @return
     */
    Date queryLastReplyTimeById(@Param("message_id") String message_id);


    /**
     * 插入一条评论
     *
     * @param message_id
     * @param replier
     * @param reply_content
     * @return
     */
    int addReplyById(@Param("message_id") int message_id, @Param("replier") String replier, @Param("reply_content") String reply_content,@Param("reply_time") Date reply_time);
}
