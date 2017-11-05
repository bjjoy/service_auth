package cn.bjjoy.service.auth.controller;

import cn.bjjoy.service.auth.base.ResponseCode;
import cn.bjjoy.service.auth.base.ResponseResult;
import cn.bjjoy.service.auth.dto.RoleDto;
import cn.bjjoy.service.auth.entity.Role;
import cn.bjjoy.service.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gxm on 2017/11/05.
 */
@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public ResponseResult getRole(@RequestParam String userUuid, @RequestParam(required = false) String permission,
//                                  String traceID){
//        List<String> permissionList = roleService.queryRoleByUser(userUuid, permission);
//        ResponseResult responseResult = new ResponseResult();
//        responseResult.setCode(ResponseCode.OK);
//        responseResult.setMsg(ResponseCode.OK_TEXT);
//        responseResult.setTraceID(traceID);
//        responseResult.setData(permissionList);
//        return responseResult;
//    }

    /**
     * 获取角色列表
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseResult getList(@RequestParam Map param, String traceID){
        Integer pageNo = (param.get("pageNo") == null ? null : Integer.valueOf(param.get("pageNo").toString()));
        Integer pageSize = (param.get("pageSize") == null ? null : Integer.valueOf(param.get("pageSize").toString()));
        if(pageNo != null && pageSize != null) {
            param.put("startRow", (pageNo - 1) * pageSize);
            param.put("pageSize", pageSize);
        }
        List<Role> roleList = roleService.getList(param);
        Map<String, Object> responseResult = new HashMap<>();
        responseResult.put("list",roleList);
        if(pageNo != null && pageSize != null) {
            Integer count = roleService.getCount(param);
            responseResult.put("count", count);
        }
        return new ResponseResult(traceID, ResponseCode.OK, ResponseCode.OK_TEXT, responseResult);
    }

    /**
     * 创建角色
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseResult create(RoleDto roleDto, String traceID){
        int count = roleService.create(roleDto);
        return new ResponseResult(traceID, ResponseCode.OK, ResponseCode.OK_TEXT, count);
    }

    /**
     * 角色详情
     */
    @RequestMapping(value = "/getRole", method = RequestMethod.GET)
    public ResponseResult getRole(@RequestParam Integer roleId, String traceID){
        Map resultMap = roleService.getRole(roleId);
        return new ResponseResult(traceID, ResponseCode.OK, ResponseCode.OK_TEXT, resultMap);
    }

    /**
     * 角色更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult update(RoleDto roleDto, String traceID){
        int count = roleService.update(roleDto);
        return new ResponseResult(traceID, ResponseCode.OK, ResponseCode.OK_TEXT, count);
    }

    /**
     * 检查角色是否可删除，有可用用户关联，禁止删除
     */
    @RequestMapping(value = "/deleteCheck", method = RequestMethod.GET)
    public ResponseResult deleteCheck(@RequestParam Integer roleId, String traceID){
        int count = roleService.getUserRoleCountByRoleId(roleId);
        ResponseResult responseResult;
        if (count > 0){
            responseResult = new ResponseResult(traceID, ResponseCode.PARAM_ERROR, "该角色仍被使用，无法删除！", count);
        }else {
            responseResult = new ResponseResult(traceID, ResponseCode.OK, ResponseCode.OK_TEXT, count);
        }
        return responseResult;
    }

    /**
     * 角色删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseResult delete(Role role, String traceID){
        role.setDelFlag("1");
        int count = roleService.delete(role);
        return new ResponseResult(traceID, ResponseCode.OK, ResponseCode.OK_TEXT, count);
    }
}