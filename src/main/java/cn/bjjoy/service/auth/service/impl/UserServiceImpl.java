package cn.bjjoy.service.auth.service.impl;

import cn.bjjoy.service.auth.persistence.dao.UserMapper;
import cn.bjjoy.service.auth.persistence.dao.UserRoleMapper;
import cn.bjjoy.service.auth.service.UserService;
import cn.bjjoy.service.auth.util.DataUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by gxm on 2017/11/02.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insert(Map param){

        //新建用户
        int count = userMapper.insertUser(param);
        if(null != param.get("roleIds") && StringUtils.isNotBlank(param.get("roleIds").toString())) {

            //添加用户角色关系
            List<String> roleIdList =this.getRoleIdList(param);
            for(String roleId : roleIdList){
                param.put("roleId", roleId);
                count += userRoleMapper.insert(param);
            }
        }
        return count;
    }

    @Override
    public int update(Map param){
        return userMapper.updateByUuid(param);
    }

    @Override
    public List<Map> getList(Map param){
        return DataUtils.getDataArray(userMapper.getList(param), Map.class);
    }

    @Override
    public Integer getCount(Map param){
        return userMapper.getCount(param);
    }

    @Override
    public Map getUserDetail(String uuid){
        return DataUtils.getData(userMapper.getByUuid(uuid),Map.class);
    }

    @Override
    public int updateUser(Map param){

        //1.更新用户
        int count = userMapper.updateByUuid(param);
        if(null != param.get("roleIds") && StringUtils.isNotBlank(param.get("roleIds").toString())) {

            //2.删除用户角色关系
            count += userRoleMapper.delete(param);

            //3.添加用户角色关系
            List<String> roleIdList =this.getRoleIdList(param);
            for(String roleId : roleIdList){
                param.put("roleId", roleId);
                count += userRoleMapper.insert(param);
            }
        }
        return count;
    }

    @Override
    public void deleteUser(Map param){

        //更新del_flag = 1
        userMapper.updateByUuid(param);
        //删除用户角色关系
        userRoleMapper.delete(param);
    }

    /**
     * 初始化角色id
     * @param param
     */
    private List<String> getRoleIdList(Map param){
        if(null == param.get("roleIds") || StringUtils.isBlank(param.get("roleIds").toString())) {
            return new ArrayList<>();
        }
        String roleIds = param.get("roleIds").toString();
        return Arrays.asList(roleIds.split("\\$\\$"));
    }
}
