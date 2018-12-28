package cn.psy.homework11.pojo;

import java.io.Serializable;

/**
 * 文章的相关信息
 */
public class Message implements Serializable {
    private int id;//文章id

    private String title;//文章标题

    private String author;//文章作者

    private String article;//文章主内容

    private String time;//文章的创建时间

    private int reply_count;//文章的评论次数


    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public String getArticle() {

        return article;
    }

    public void setArticle(String article) {

        this.article = article;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", article='" + article + '\'' +
                ", time='" + time + '\'' +
                ", reply_count='" + reply_count + '\'' +
                '}';
    }
}
