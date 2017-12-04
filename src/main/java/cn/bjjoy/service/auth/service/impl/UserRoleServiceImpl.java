package cn.bjjoy.service.auth.service.impl;

import cn.bjjoy.service.auth.dto.UserRoleDto;
import cn.bjjoy.service.auth.persistence.dao.UserRoleMapper;
import cn.bjjoy.service.auth.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by bjjoy on 2017/11/02.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insert(Map param){
        return userRoleMapper.insert(param);
    }

    @Override
    public int update(Map param){
        return userRoleMapper.update(param);
    }

    @Override
    public int delete(Map param){
        return userRoleMapper.delete(param);
    }

    @Override
    public List<UserRoleDto> getUserRoleList(List<String> userUuidList){
        return userRoleMapper.getUserRoleList(userUuidList);
    }
}
