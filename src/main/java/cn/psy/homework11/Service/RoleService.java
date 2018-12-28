package cn.psy.homework11.Service;

import cn.psy.homework11.pojo.Role;

import java.util.List;

public interface RoleService {
    /**
     * 根据角色查询id
     * @param role
     * @return int
     */
    int queryIdByRole(String role);

    /**
     * 添加角色
     * @param role
     * @return int
     */
    int addRole(String role);

    /**
     * 根据id查询角色
     * @param role_id
     * @return
     */
    Role queryRoleById(int role_id);

    /**
     *
     * 查询角色在库中出现次数
     * @param role
     * @return List
     */
    List<Role> queryRoleTimes(String role);

    /**
     * 查询所有角色
     * @return List
     */
    List<Role> queryAllRoles();
}
