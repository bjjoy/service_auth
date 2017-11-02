package cn.bjjoy.service.auth.service;

import cn.bjjoy.service.auth.dto.UserRoleDto;

import java.util.List;
import java.util.Map;

/**
 * Created by guoxiaoming on 2017/9/22.
 */
public interface UserRoleService {

    /**
     * 新建用户角色关系
     */
    int insert(Map param);

    /**
     * 更新用户角色关系
     */
    int update(Map param);

    /**
     * 删除用户角色关系
     */
    int delete(Map param);

    /**
     * 根据用户uuid获取user_role信息
     */
    List<UserRoleDto> getUserRoleList(List<String> userUuidList);
}
