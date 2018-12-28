package cn.psy.homework11.dao;

import cn.psy.homework11.pojo.Power;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PowerDAO {
    /**
     * 根据角色设置用户权限
     * @param role_id
     * @param add_p
     * @param delete_p
     * @param update_p
     * @param see
     * @param reply
     * @param manage
     * @return int
     */
    int setPowerByRole(@Param("role_id") int role_id,@Param("add_p") int add_p,@Param("delete_p") int delete_p,
                       @Param("update_p") int update_p,@Param("see") int see,@Param("reply") int reply,@Param("manage") int manage);


    /**
     * 根据角色查询对应权限
     * @param role_id
     * @return Power
     */
    Power queryPowerByRole(@Param("role_id") int role_id);
}
