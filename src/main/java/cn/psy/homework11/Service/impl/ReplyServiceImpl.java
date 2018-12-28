package cn.psy.homework11.Service.impl;

import cn.psy.homework11.Service.ReplyService;
import cn.psy.homework11.dao.ReplyDAO;
import cn.psy.homework11.pojo.Reply;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 评论接口的实现类
 */
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyDAO replyDAO;

    /**
     * 根据文章id查找评论
     *
     * @param message_id
     * @return Reply
     */
    @Override
    public List<Reply> queryReplyByMessage(int message_id) {
        return replyDAO.queryReplyByMessage(message_id);
    }



    /**
     * 根据文章id找最后评论时间
     *
     * @param message_id
     * @return Date
     */
    @Override
    public Date queryLastReplyTimeById(String message_id) {
        return replyDAO.queryLastReplyTimeById(message_id);
    }


    /**
     * 根据文章id添加评论
     * @param message_id;
     * @param replier
     * @param reply_content
     *
     * @return int
     */
    @Override
    public int addReplyById(int message_id, String replier, String reply_content,Date reply_time) {
        return replyDAO.addReplyById(message_id, replier, reply_content, reply_time);
    }



}
