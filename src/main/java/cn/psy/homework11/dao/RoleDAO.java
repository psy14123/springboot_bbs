package cn.psy.homework11.dao;

import cn.psy.homework11.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
@Mapper
public interface RoleDAO {


    /**
     * 添加角色
     * @param role
     * @return int
     */
    int addRole(@Param("role") String role);

    /**
     * 根据角色查询id
     * @param role
     * @return id
     */
    int queryIdByRole(@Param("role") String role);

    /**
     * 根据id查询角色
     * @param role_id
     * @return Role
     */
    Role queryRoleById(@Param("role_id") int role_id);

    /**
     * 查询库中角色出现的次数
     * @param role
     * @return List
     */
    List<Role> queryRoleTimes(@Param("role") String role);


    /**
     * 查询库中所有角色名
     * @return List
     */
    List<Role> queryAllRoles();
}
