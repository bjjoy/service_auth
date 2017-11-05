package cn.bjjoy.service.auth.controller;

import cn.bjjoy.service.auth.base.ResponseCode;
import cn.bjjoy.service.auth.base.ResponseResult;
import cn.bjjoy.service.auth.dto.UserDto;
import cn.bjjoy.service.auth.dto.UserRoleDto;
import cn.bjjoy.service.auth.entity.Menu;
import cn.bjjoy.service.auth.entity.Role;
import cn.bjjoy.service.auth.entity.User;
import cn.bjjoy.service.auth.service.MenuService;
import cn.bjjoy.service.auth.service.RoleService;
import cn.bjjoy.service.auth.service.UserRoleService;
import cn.bjjoy.service.auth.service.UserService;
import cn.bjjoy.service.auth.util.DataUtils;
import cn.bjjoy.service.auth.util.EncryptUtils;
import cn.bjjoy.service.auth.util.IdUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gxm on 2017/8/28.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;


    /**
     * 生成用户
     * @param userDto
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseResult insert(UserDto userDto){

        if(StringUtils.isEmpty(userDto.getPassword())){
            return new ResponseResult(null, ResponseCode.PARAM_ERROR, ResponseCode.PARAM_ERROR_TEXT, "password");
        }
        Map param = DataUtils.getData(userDto, Map.class);
        String uuid = IdUtils.getUuid();
        param.put("uuid", uuid); //user表uuid
        param.put("userUuid", uuid); //user_role表user_uuid
        param.put("password", EncryptUtils.encryptMD5(userDto.getPassword()));
        this.userService.insert(param);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, uuid);
    }

    /**
     * 获取用户详情
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseResult getUser(@RequestParam String uuid){
        Map<String, Object> resultMap = this.userService.getUserDetail(uuid);
        List<Role> roleList = this.roleService.getList(new HashMap());
        resultMap.put("roleList", roleList);
        UserDto userDto = DataUtils.getData(resultMap, UserDto.class);

        //获取角色信息
        List<String> codeList = new ArrayList<>();
        codeList.add(uuid);
        List<UserRoleDto> userRoleDtoList = this.userRoleService.getUserRoleList(codeList);

        //用户对应角色列表是否被选中
        for (UserRoleDto userRoleDto : userDto.getRoleList()){
            for (UserRoleDto selectRole : userRoleDtoList){
                if (userRoleDto.getId().equals(selectRole.getRoleId())){
                    userRoleDto.setIsSelect(1);
                }
            }
        }

        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, userDto);
    }

    /**
     * 更新用户信息
     * @param userDto
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult update(UserDto userDto){

        Map param = DataUtils.getData(userDto, Map.class);
        if(StringUtils.isEmpty(userDto.getUuid())){
            return new ResponseResult(null, ResponseCode.PARAM_ERROR, ResponseCode.PARAM_ERROR_TEXT, "uuid");
        }
        if(StringUtils.isNotBlank(userDto.getPassword())){
            param.put("password", EncryptUtils.encryptMD5(userDto.getPassword()));
        }
        param.put("userUuid", userDto.getUuid());
        int count = userService.updateUser(param);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, count);
    }

    /**
     * 获取用户列表
     * @param param
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseResult getList(@RequestParam Map param){
        Integer pageNo = (param.get("pageNo") == null ? 1 : Integer.valueOf(param.get("pageNo").toString()));
        Integer pageSize = (param.get("pageSize") == null ? 20 : Integer.valueOf(param.get("pageSize").toString()));
        param.put("startRow", (pageNo - 1) * pageSize);
        List<Map> userMapList = this.userService.getList(param);
        //没有用户数据直接返回
        if(userMapList == null || userMapList.size() == 0){
            Map<String, Object> responseResult = new HashMap<>();
            responseResult.put("userList", new ArrayList<>());
            responseResult.put("count", 0);
            return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, responseResult);
        }

        List<UserDto> userList = DataUtils.getDataArray(userMapList, UserDto.class);

        List<String> userUuidList = new ArrayList<>();
        for(UserDto userDto : userList){
            userUuidList.add(userDto.getUuid());
        }
        //获取用户角色信息
        List<UserRoleDto> userRoleVOList = this.userRoleService.getUserRoleList(userUuidList);
        for(UserDto userDto : userList){
            //添加角色list
            for(UserRoleDto userRoleDto : userRoleVOList){
                if(userDto.getUuid().equals(userRoleDto.getUserUuid())){
                    if(userDto.getRoleList() == null){
                        userDto.setRoleList(new ArrayList<>());
                        userDto.getRoleList().add(userRoleDto);
                    }else{
                        userDto.getRoleList().add(userRoleDto);
                    }
                }
            }
        }

        Integer count = this.userService.getCount(param);
        Map<String, Object> responseResult = new HashMap<>();
        responseResult.put("userList",userList);
        responseResult.put("count", count);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, responseResult);
    }

    /**
     * 删除用户
     * @param user
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseResult delete(User user){

        if(StringUtils.isEmpty(user.getUuid())){
            return new ResponseResult(null, ResponseCode.PARAM_ERROR, ResponseCode.PARAM_ERROR_TEXT, "uuid");
        }
        Map param = DataUtils.getData(user, Map.class);
        param.put("userUuid", user.getUuid());
        param.put("delFlag","1");
        userService.deleteUser(param);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, user.getUuid());
    }
}
