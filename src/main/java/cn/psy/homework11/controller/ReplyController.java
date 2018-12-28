package cn.psy.homework11.controller;

import cn.psy.homework11.Service.MessageService;
import cn.psy.homework11.Service.ReplyService;
import cn.psy.homework11.pojo.Message;
import cn.psy.homework11.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 评论相关
 */
@Controller
public class ReplyController {
    private Logger logger = Logger.getLogger(String.valueOf(ReplyController.class));

    @Autowired
    private ReplyService replyService;

    @Autowired
    private MessageService messageService;

    /**
     * 添加评论
     * @param message_id
     * @param replier
     * @param reply_content
     * @param session
     * @return String
     */
    @RequestMapping("/addReplyById")
    public String addReply(@Param("message_id") int message_id, String replier, String reply_content,  HttpSession session){

        User user = (User) session.getAttribute("user");
        String name = user.getName();
        int user_id = user.getId();
        Message message = messageService.queryMessageById(message_id);//根据id查询文章
        String title = message.getTitle();
        String article = message.getArticle();
        String author = message.getAuthor();    //绑定信息


        Date time = new Date();
        int flag = replyService.addReplyById(message_id,name,reply_content,time);//根据文章id添加评论
        int reply_count = message.getReply_count()+1;
        messageService.updateMessageById(message_id,title,article,user_id,author,time,reply_count);//更新文章的信息


            return "redirect:/administerMessage";//返回主页面


    }

    /**
     * 去往评论页面
     * @param model
     * @param message_id
     * @return String
     */
    @RequestMapping("/toAddReply")
    public String toAddReply(Model model,int message_id){
        model.addAttribute("message_id",message_id);
        return "addReply";
    }


}
