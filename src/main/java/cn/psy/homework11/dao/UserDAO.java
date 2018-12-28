package cn.psy.homework11.dao;

import cn.psy.homework11.pojo.Role;
import cn.psy.homework11.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * user相关方法的接口
 */
@Repository
@Mapper
public interface UserDAO {
    /**
     * 查询用户是否在user表中，在登录和注册时检验使用
     * @param user
     * @return User
     */
    User queryUser(@Param("user") User user);

    /**
     * 根据用户的电话查找用户是否存在
     * @param tel
     * @return User
     */
    User queryUserByTel(@Param("tel") String tel);

    /**
     * 注册用户
     * @param name
     * @param tel
     * @param password
     * @return int
     */
    int registerUser(@Param("name") String name,@Param("tel") String tel,@Param("password") String password,@Param("role_id") int role_id);

    /**
     *根据用户id设置角色
     * @param id
     * @return int
     */
    int setUserRoleByID(@Param("id") int id);

    /**
     * 根据用户的电话查找id
     * @param tel
     * @return int
     */
    int queryIdByTel(@Param("tel") String tel);


    /**
     * 根据用户的id查询用户角色
     * @param user_id
     *
     * @return int
     */
    Role queryRoleById(@Param("user_id") int user_id);

    /**
     *
     * 把所有用户查询出来
     * @return List
     */
    List<User> queryAllUsers();


    /**
     *根据id更改用户角色
     * @param user_id
     * @param role
     * @return
     */
    int editRoleById(int user_id,int role);


    /**
     * 根据用户名字来修改角色
     * @param name
     * @param role_id
     * @return int
     */
    int updateRoleByName( @Param("role_id") int role_id,@Param("name") String name);


    /**
     * 根据角色查询出对应的用户
     * @return List
     */
    List<User> queryUsersByRole(int role);
}
