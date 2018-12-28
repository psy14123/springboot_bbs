package cn.psy.homework11.Service;

import cn.psy.homework11.pojo.Role;
import cn.psy.homework11.pojo.User;

import java.util.List;

/**
 * user相关方法的接口
 */

public interface UserService {
    /**
     * /登陆时使用
     * @param user
     * @return User
     */
    User queryUser (User user);

    /**
     * 使用电话查询用户
     * @param tel
     * @return User
     */
    User queryUserByTel(String tel);

    /**
     * 注册用户
     * @param name
     * @param tel
     * @param password
     * @param role_id
     * @return int
     */
    int registerUser(String name,String tel,String password,int role_id);

    /**
     * 根据用户id 设置用户的角色，1为普通用户，2为管理员
     * @param id
     * @return int
     */
    int setUserRoleByID(int id );

    /**
     * 根据用户的手机查找出用户的id
     * @param tel
     * @return int
     */
    int queryIdByTel(String tel);

    /**
     * 根据用户的id查询角色
     * @param role_id
     * @return Role
     */
    Role queryRoleById (int role_id);

    /**
     * 查找所有普通会员
     * @return List
     */
    List<User> queryAllUsers ();

    /**
     * 根据user_id更改用户角色
     * @param user_id
     * @param role
     * @return int
     */
    int editRoleById(int user_id,int role);

    /**
     * 根据用户名字修改角色
     * @param role_id
     * @param name
     * @return int
     */
    int updateRoleByName(int role_id,String name);

    /**
     * 根据角色查询普通用户
     * @param role
     * @return List
     */
    List<User> queryUsersByRole(int role);
}
