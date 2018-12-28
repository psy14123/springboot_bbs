package cn.psy.homework11.Service;

import cn.psy.homework11.pojo.Power;

public interface PowerService  {
    /**
     * 根据角色设置相对权限
     * @param power
     * @return int
     */
    int setPowerByRole(Power power);

    /**
     * 根据角色查询权限
     * @param role_id
     * @return Power
     */
    Power queryPowerByRole(int role_id);
}
