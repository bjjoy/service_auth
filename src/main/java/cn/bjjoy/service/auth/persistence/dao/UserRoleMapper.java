package cn.bjjoy.service.auth.persistence.dao;

import cn.bjjoy.service.auth.dto.UserRoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by bjjoy on 2017/9/22.
 */
@Mapper
@Repository
public interface UserRoleMapper {

    int insert(Map param);

    int update(Map param);

    int delete(Map param);

    /**
     * 根据用户uuid获取user_role信息
     */
    List<UserRoleDto> getUserRoleList(@Param("userUuidList") List<String> userUuidList);

    /**
     * 获取可用用户对应角色数量
     */
    int getUserRoleCountByRoleId(@Param("roleId") Integer roleId);

}
