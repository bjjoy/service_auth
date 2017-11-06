package cn.bjjoy.service.auth.controller;

import cn.bjjoy.service.auth.base.ResponseCode;
import cn.bjjoy.service.auth.base.ResponseResult;
import cn.bjjoy.service.auth.entity.Menu;
import cn.bjjoy.service.auth.service.MenuService;
import cn.bjjoy.service.auth.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by gxm on 2017/11/05.
 */
@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseResult getMenu(@RequestParam String userUuid,
//                                  @RequestParam(required = false) String traceID) {
//        List<Menu> menuList = menuService.queryMenuByUser(userUuid);
//        ResponseResult responseResult = new ResponseResult();
//        responseResult.setCode(ResponseCode.OK);
//        responseResult.setMsg(ResponseCode.OK_TEXT);
//        responseResult.setTraceID(traceID);
//        responseResult.setData(menuList);
//        return responseResult;
//    }

    /**
     * 新建菜单
     * @param menu
     * @param traceID
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseResult createMenu(Menu menu, String traceID){

        menuService.insert(menu);
        return new ResponseResult(traceID,ResponseCode.OK, ResponseCode.OK_TEXT, menu.getId());
    }

    /**
     * 菜单列表
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseResult getList(Map param, String traceID){
        List<Menu> menuList = menuService.getList(param);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResponseCode.OK);
        responseResult.setMsg(ResponseCode.OK_TEXT);
        responseResult.setTraceID(traceID);
        responseResult.setData(menuList);
        return responseResult;
    }

    /**
     * 菜单详情
     */
    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    public ResponseResult getMenu(@RequestParam Integer id, String traceID){
        Menu menu = menuService.getMenu(id);
        Map resultMap = DataUtils.getData(menu, Map.class);
        if(null != menu.getParentId()) {
            Menu parentMenu = menuService.getMenu(menu.getParentId());
            resultMap.put("parentName", parentMenu.getName());
        }
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResponseCode.OK);
        responseResult.setMsg(ResponseCode.OK_TEXT);
        responseResult.setTraceID(traceID);
        responseResult.setData(resultMap);
        return responseResult;
    }

    /**
     * 菜单详情
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult update(Menu menu, String traceID){
        int count = menuService.update(menu);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResponseCode.OK);
        responseResult.setMsg(ResponseCode.OK_TEXT);
        responseResult.setTraceID(traceID);
        responseResult.setData(count);
        return responseResult;
    }

    /**
     * 菜单删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseResult delete(Menu menu, String traceID){
        menu.setDelFlag("1");
        int count = menuService.delete(menu);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResponseCode.OK);
        responseResult.setMsg(ResponseCode.OK_TEXT);
        responseResult.setTraceID(traceID);
        responseResult.setData(count);
        return responseResult;
    }
}