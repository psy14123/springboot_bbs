package cn.psy.homework11.Service.impl;

import cn.psy.homework11.Service.PowerService;
import cn.psy.homework11.dao.PowerDAO;
import cn.psy.homework11.pojo.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerDAO powerDAO;

    /**
     *
     * @param power
     * @retur int
     */
    @Override
    public int setPowerByRole(Power power) {
        return powerDAO.setPowerByRole(power.getRole_id(),power.getAdd_p(),power.getDelete_p(),
                power.getUpdate_p(), power.getSee(),power.getReply(),power.getManage());
    }


    /**
     * 根据角色查询对应的权限
     * @param role_id
     * @return power
     */
    @Override
    public  Power queryPowerByRole(int role_id){
        return powerDAO.queryPowerByRole(role_id);
    }
}
