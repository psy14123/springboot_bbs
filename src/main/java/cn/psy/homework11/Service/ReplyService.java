package cn.psy.homework11.Service;

import cn.psy.homework11.pojo.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 评论相关的接口
 */
public interface ReplyService {
    /**
     * 根据文章的id查找评论
     * @return Reply
     */
    List<Reply> queryReplyByMessage(int message_id);


    /**
     * 根据文章的id查找最新评论时间
     * @param message_id
     * @return Date
     */
    Date queryLastReplyTimeById(String message_id);

    /**
     * 添加评论
     * @param replier
     * @param reply_content
     * @param message_id;
     * @return int
     */
    int addReplyById( int message_id,String replier,String reply_content,Date reply_time);
}
