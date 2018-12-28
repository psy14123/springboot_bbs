package cn.psy.homework11.controller;

import cn.psy.homework11.Service.*;
import cn.psy.homework11.dao.MessageDAO;
import cn.psy.homework11.dao.UserDAO;
import cn.psy.homework11.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * message处理相关
 */
@Controller
public class MessageController {
    private Logger logger = Logger.getLogger(String.valueOf(MessageController.class));

    final static int one = 1;
    final static int two = 2;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PowerService powerService;



    /**
     * 去往管理员页面
     *
     * @return String
     */
    @RequestMapping("/toAdministerMessage")
    public String toAdministerMessage() {
        return "manage_message";
    }


    /**
     * 跳转到添加消息界面
     *
     * @return String
     */
    @RequestMapping("/toAddMessage")
    public String toAddMessage() {
        return "add";
    }




    /**
     * 查询一个消息，将该消息内容展示出到一个页面用于编辑
     * @param id
     * @return List
     */
    @RequestMapping("/theMessage")
    public Message queryMessageById(@RequestParam("id") int id) {
        Message message = messageService.queryMessageById(id);
        if (null == message) {
            return null;
        } else {
            return message;
        }
    }



    /**
     * 普通会员消息页面
     * @param model
     * @return String
     */
    @RequestMapping("/commonMessage")
    public String commonMessage(Model model) {
        List<Message> messages = messageService.queryAllMessages();
        model.addAttribute("message_list", messages);
        model.addAttribute("replyService", replyService);
        if (null == messages){
            return null;
        }else{
        return "common_message";
        }
    }


    /**
     * 管理员消息页面
     *
     * @return
     */
    @RequestMapping("/administerMessage")
    public String administerMessage(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int role_id = user.getRole_id();
        Role role = roleService.queryRoleById(role_id);
        Power power = powerService.queryPowerByRole(role_id);
        List<Message> messages = messageService.queryAllMessages();//取出相关数据


        model.addAttribute("power",power);
        model.addAttribute("message_list", messages);
        model.addAttribute("powerService",powerService);
        model.addAttribute("replyService", replyService);//将数据放入model


        if (null == messages){
            return null;
        }else {
            return "manage_message";//非空判断
        }
    }

    /**
     * 添加一个文章
     *
     * @param title
     * @param article
     * @return String
     */
    @RequestMapping("/addMessage")
    public String addMessage(@RequestParam("title") String title, @RequestParam("article") String article,
                             HttpSession session) throws Exception{

        User user = (User) session.getAttribute("user");
        String name = user.getName();
        int user_id = user.getId();
        Date time = new Date();//取出相关数据

        int flag = messageService.addMessage(title, article, user_id, name, time, 0);
        if (one == flag) {

            return "redirect:/administerMessage";
        }else {
            return null;
        }
    }

    /**
     * 根据id来删除文章
     *
     * @param id
     * @return String
     */
    @RequestMapping("/deleteMessageById")
    public String deleteMessage(int id,HttpSession session) {
        int flag = messageService.deleteMessageById(id);
//        User user = (User) session.getAttribute("user");
//        int role = user.getRole_id();
        if (one == flag){
        return "redirect:/administerMessage";
        }else {
            return null;
        }

    }



    /**
     * 根据文章id来修改文章
     *
     * @param title
     * @param article
     * @return String
     */
    @RequestMapping("/updateMessageById")
    public String updateMessageById(int id,String title, String article,HttpSession session) {
        User user = (User) session.getAttribute("user");
        String author = user.getName();
        List<Reply> list = replyService.queryReplyByMessage(id);
        int reply_count = list.size();
        int user_id = user.getId();
        int role = user.getRole_id();
        Date time = new Date();//取出相关数据


        int flag = messageService.updateMessageById(id, title, article, user_id, author, time, reply_count);
        if (one == flag){

            return "redirect:/administerMessage";//非空判断
        }else {
            return null;
        }
    }


    /**
     * 根据用户的用户名来查找文章
     *
     * @param session
     * @return Model
     */
    @RequestMapping("/myMessage")
    public String myMessage(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        String author = user.getName();
        List<Message> myList = messageService.queryMessagesByAuthor(author);//根据作者查出文章

        model.addAttribute("myList",myList);
        model.addAttribute("userService",userService);

//        mv.addObject("myList", myList);
//        mv.addObject("userService", userService);
        return "my_message";
    }


    /**
     * 去往编辑页面
     * @param id
     * @param model
     * @return String
     */

    @RequestMapping("/toEditMessage")
    public String toEditMessage(@RequestParam("id") int id, Model model) {
        model.addAttribute("id", id);
        Message message =  messageService.queryMessageById(id);//根据id查出message
        model.addAttribute("message",message);
        return "edit";
    }


    /**
     * 展示具体内容
     * @param message_id
     * @param model
     * @return String
     */
    @RequestMapping("/messageDetails")
    public String messageDetails(@RequestParam("message_id") int message_id,Model model){

        Message message = messageService.queryMessageById(message_id);

        List<Reply> replies_list =  replyService.queryReplyByMessage(message_id);//根据文章id查出评论
        model.addAttribute("message_id",message_id);
        model.addAttribute(message);
        model.addAttribute(replies_list);
        if (null != replies_list){
        return "theMessageDetail";
        }else {
            return null;
        }
    }


}
