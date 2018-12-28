package cn.psy.homework11.Service.impl;

import cn.psy.homework11.Service.UserService;
import cn.psy.homework11.dao.UserDAO;
import cn.psy.homework11.pojo.Role;
import cn.psy.homework11.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserService接口的应用
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    /**
     * 查询用户是否在表中
     * @param user
     * @return User
     */
    @Override
    public User queryUser(User user) {
        return userDAO.queryUser(user);
    }

    /**
     * 根据用户电话查找用户
     * @param tel
     * @return User
     */
    @Override
    public User queryUserByTel(String tel) {
        return userDAO.queryUserByTel(tel);
    }

    /**
     * 注册用户
     * @param name
     * @param tel
     * @param password
     * @return int
     */
    @Override
    public int registerUser(String name,String tel,String password,int role_id){
        return userDAO.registerUser(name,tel,password,2);
    }


    /**
     * 根据用户id设置用户的角色，默认都为普通用户
     * @param id
     * @return int
     */
    @Override
    public int setUserRoleByID(int id){

        return userDAO.setUserRoleByID(id);
    }

    /**
     * 根据用户的电话获取用户的id
     * @param tel
     * @return int
     */
    @Override
    public int queryIdByTel(String tel){
        return userDAO.queryIdByTel(tel);
    }

    /**
     * 根据id查找用户角色
     * @param role_id
     * @return Role
     */
    @Override
    public Role queryRoleById(int role_id){
        return userDAO.queryRoleById(role_id);
    }

    /**
     *
     * 把所有普通用户查询出来
     * @return User
     */
    @Override
    public List<User> queryAllUsers(){
        return userDAO.queryAllUsers();
    }


    /**
     * 根据用户id更改角色
     * @param user_id
     * @param role
     * @return int
     */
    @Override
    public int editRoleById(int user_id,int role){
        return userDAO.editRoleById(user_id,role);
    }

    /**
     * 根据用户的昵称改角色
     * @param name
     * @param role_id
     * @return int
     */
    @Override
    public int updateRoleByName( int role_id,String name) {
        return userDAO.updateRoleByName(role_id,name);
    }

    /**
     * 根据用户的角色查找所有普通会员
     * @param role
     * @return List
     */
    @Override
    public List<User> queryUsersByRole(int role) {
        return userDAO.queryUsersByRole(role);
    }

}
