package cn.psy.homework11.controller;

import cn.psy.homework11.Service.PowerService;
import cn.psy.homework11.Service.RoleService;
import cn.psy.homework11.Service.UserService;
import cn.psy.homework11.pojo.Power;
import cn.psy.homework11.pojo.Role;
import cn.psy.homework11.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

/**
 * 角色相关
 */
@Controller
public class RoleController {

    private Logger logger = Logger.getLogger(String.valueOf(RoleController.class));
    final int one = 1;
    final int zero = 0;
@Autowired
private RoleService roleService;
@Autowired
private UserService userService;
@Autowired
private PowerService powerService;

    /**
     * 新建一个角色
     * @param role
     * @param add
     * @param delete
     * @param update
     * @param see
     * @param reply
     * @param manage
     * @param session
     * @return String
     */

    @RequestMapping("/createRole")
    @ResponseBody
    public String createRole (String role, int add, int delete, int update, int see, int reply,int manage, HttpSession session){
        User user = (User) session.getAttribute("user");
        int user_id = user.getId();
        Role role1 = new Role();


        List<Role> times= roleService.queryRoleTimes(role);//查询查询库中角色是否已经存在

        int flag = roleService.addRole(role);
        int role_id = roleService.queryIdByRole(role);


        Power power1 = new Power();
        power1.setRole_id(role_id);
        power1.setAdd_p(add);
        power1.setDelete_p(delete);
        power1.setUpdate_p(update);
        power1.setSee(see);
        power1.setReply(reply);
        power1.setManage(manage);//将前台获取的数据绑定在power上

        if (times.size() != zero) {
            String jsonStr = "{\"errorCode\":\"08\",\"errorMessage\":\"角色已存在！\"}";
            return jsonStr;
        }else {
            int flag1 = powerService.setPowerByRole(power1);
            String jsonStr = "{\"errorCode\":\"07\",\"errorMessage\":\"创建成功！\"}";
            return jsonStr;
        }

    }



    /**
     * 创建角色
     * @return String
     */
    @RequestMapping("/toCreateRole")
    public String toCreateRole(){

        return "createRole";
    }



    /**
     * 改变用户角色
     * @param name
     * @param role
     * @return String
     */
    @RequestMapping("/updateRole")
    @ResponseBody
    public String updateRole(@RequestParam("name") String name,@RequestParam("role") String role){
        int role_id = roleService.queryIdByRole(role);

        int flag = userService.updateRoleByName(role_id,name);//从前台获取到角色名加进库中

        if (one == flag){
            String jsonStr = "{\"errorCode\":\"99\",\"errorMessage\":\"修改成功\"}";
            return jsonStr;
        }
        return "administerMessage";

    }

}
