package cn.bjjoy.service.auth.service;

import java.util.List;
import java.util.Map;

/**
 * Created by gxm on 2017/11/02.
 */
public interface UserService {

    /**
     * 创建用户
     */
    int insert(Map param);

    /**
     * 更新用户
     */
    int update(Map param);

    /**
     * 获取用户列表
     */
    List<Map> getList(Map param);

    /**
     * 获取用户数量
     */
    Integer getCount(Map param);

    /**
     * 获取用户详情
     */
    Map getUserDetail(String uuid);

    /**
     * 更新用户相关信息
     */
    int updateUser(Map param);

    /**
     * 删除用户相关信息（del_flag更新为1）
     */
    void deleteUser(Map param);
}
