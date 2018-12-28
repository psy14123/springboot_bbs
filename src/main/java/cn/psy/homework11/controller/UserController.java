package cn.psy.homework11.controller;

import cn.psy.homework11.Service.PowerService;
import cn.psy.homework11.Service.RoleService;
import cn.psy.homework11.Service.UserService;
import cn.psy.homework11.pojo.Power;
import cn.psy.homework11.pojo.Role;
import cn.psy.homework11.pojo.User;
import cn.psy.homework11.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 用户相关的controller
 */

@Controller
public class UserController {
    final int one = 1;
    final int zero = 0;
    private Logger logger = Logger.getLogger(String.valueOf(UserController.class));//日志

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private PowerService powerService;

    @Autowired
    private RoleService roleService;

    /**
     * 去往登陆界面
     *
     * @return String
     */
    @RequestMapping("/tologin")
    public String toLogin() {

        return "login";
    }



    /**
     * 去往注册页面
     *
     * @return String
     */
    @RequestMapping("/toregister")
    public String toRegister() {

        return "register";
    }



    /**
     * 执行登陆的判断
     *
     * @param tel
     * @param password
     * @param request
     * @return String
     */

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam(value = "tel") String tel, @RequestParam(value = "password") String password,
                        HttpServletRequest request, Model model) {


//        User uncheckedUser = new User();
//        uncheckedUser.setTel(tel);
//        password = MD5Util.getMD5(password);
//        uncheckedUser.setPassword(password);
        String client_tel = tel;
        String client_password = MD5Util.getMD5(password);

        User user = userService.queryUserByTel(tel);//根据用户输入的电话查询用户是否存在
        if (null == user) {
            String jsonStr = "{\"errorCode\":\"5\",\"errorMessage\":\"用户不存在\"}";
            return jsonStr;
        }
        int id = user.getId();


        int count = redisTemplate.opsForValue().get("error" + tel) == null ?
                0 : (int) redisTemplate.opsForValue().get("error" + tel);
        if (client_tel.equals(user.getTel()) && client_password.equals(user.getPassword())) {

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            int role_id = user.getRole_id();


            Power power = powerService.queryPowerByRole(role_id);
            model.addAttribute("power", power);//查询角色权限将属性加入power

            if (null != power) {
                String jsonStr = "{\"errorCode\":\"10\",\"errorMessage\":\"登陆成功\"}";//非空判断
                return jsonStr;
            }
            redisTemplate.delete("error" + tel);

            if (count >= 3) {
                String jsonStr = "{\"errorCode\":\"404\",\"errorMessage\":\"您已尝试太多次，请休息两分钟再试\"}";
                count++;
                redisTemplate.opsForValue().set("error" + tel, count, 120, TimeUnit.SECONDS);
                return jsonStr;
            } else if (!client_password.equals(user.getPassword()) || !client_tel.equals(user.getPassword())) {
                String jsonStr = "{\"errorCode\":\"2\",\"errorMessage\":\"密码或手机号输入错误，错误输入三次后您的账户将会被锁定！\"}";
                count++;
                redisTemplate.opsForValue().set("error" + tel, count, 60, TimeUnit.SECONDS);//如果用户输错第一次后一段时间没有操作，自动清除记录
                return jsonStr;
            }

        }
        return null;
    }


    /**
     * 执行注册的判断
     *
     * @param tel
     * @param password
     * @param name
     * @param request
     * @return String
     */
    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestParam(value = "tel") String tel, @RequestParam(value = "password") String password,
                           @RequestParam(value = "name") String name, @RequestParam(value = "code") String client_code,
                           HttpServletRequest request) {
        User uncheckedUser = userService.queryUserByTel(tel);
        String server_code = stringRedisTemplate.opsForValue().get(tel);

        if (null != uncheckedUser) {
            String jsonStr = "{\"errorCode\":\"01\",\"errorMessage\":\"用户已存在！\"}";
            return jsonStr;
        } else if (!server_code.equals(client_code)) {
            String jsonStr = "{\"errorCode\":\"02\",\"errorMessage\":\"验证码不正确！\"}";
            return jsonStr;
        } else {
            password = MD5Util.getMD5(password);
            int flag = userService.registerUser(name, tel, password,2);//将用户的角色默认设置为2，即普通用户
            int id = userService.queryIdByTel(tel);

            String jsonStr = "{\"errorCode\":\"03\",\"errorMessage\":\"success\"}";
            System.out.println("注册成功，即将去往登录页面");
            return jsonStr;
        }


    }



    /**
     * 查找所有普通用户
     * @return String
     */
    @RequestMapping("/allCommonUsers")
    public String queryAllCommonUser(Model model) {
        List<User> user_list = userService.queryAllUsers();
        List<Role> role_list = roleService.queryAllRoles();//查到所有的角色和用户，用户用户管理页面

        model.addAttribute("roleList", role_list);
        model.addAttribute("userList", user_list);
        return "updateRole";

    }


}
