package cn.psy.homework11.Service.impl;

import cn.psy.homework11.Service.RoleService;
import cn.psy.homework11.dao.RoleDAO;
import cn.psy.homework11.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;


    /**
     * 根据角色查询id
     * @param role
     * @return int
     */
    @Override
    public int queryIdByRole(String role) {
        return roleDAO.queryIdByRole(role);
    }

    /**
     * 添加角色
     * @param role
     * @return int
     */
    @Override
    public int addRole(String role) {
        return roleDAO.addRole(role);
    }

    /**
     * 根据id查询角色
     * @param role_id
     * @return
     */
    @Override
    public Role queryRoleById(int role_id) {
        return roleDAO.queryRoleById(role_id);
    }

    /**
     * 查询角色在库中出现次数
     * @param role
     * @return
     */
    @Override
    public List<Role> queryRoleTimes(String role) {
        return roleDAO.queryRoleTimes(role);
    }

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> queryAllRoles() {
        return roleDAO.queryAllRoles();
    }


}
