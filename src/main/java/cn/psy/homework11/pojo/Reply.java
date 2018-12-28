package cn.psy.homework11.pojo;

import java.util.Date;

/**
 *评论相关
 */
public class Reply {
    private String replier;//评论人

    private Date reply_time;//评论时间

    private String reply_content;//评论内容

    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    public Date getReply_time() {
        return reply_time;
    }

    public void setReply_time(Date reply_time) {
        this.reply_time = reply_time;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replier='" + replier + '\'' +
                ", reply_time=" + reply_time +
                ", reply_content='" + reply_content + '\'' +
                '}';
    }
}
