package cn.bjjoy.service.auth.service;

import cn.bjjoy.service.auth.dto.RoleDto;
import cn.bjjoy.service.auth.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by bjjoy on 2017/11/02.
 */
public interface RoleService {

    /**
     * 查询用户所拥有的菜单和按钮权限
     * @param userUuid 用户ID
     * @param permission 权限前缀
     */
//    List<String> queryRoleByUser(String userUuid, String permission);

    /**
     * 获取角色列表
     */
    List<Role> getList(Map param);

    /**
     * 获取角色列表数量
     */
    int getCount(Map param);

    /**
     * 角色创建
     */
    int create(RoleDto roleDto);

    /**
     * 根据id获取角色详情
     * @param id
     * @return
     */
    Map getRole(Integer id);

    /**
     * 更新角色信息
     */
    int update(RoleDto roleDto);

    /**
     * 删除角色信息（更新del_flag）
     */
    int delete(Role role);

    /**
     * 根据角色id获取可用用户的数量
     */
    int getUserRoleCountByRoleId(Integer roleId);
}
