package cn.bjjoy.service.auth.service;

import cn.bjjoy.service.auth.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by gxm on 2017/11/02.
 */
public interface MenuService {

//    List<Menu> queryMenuByUser(String userUuid);

    /**
     * 新建菜单
     * @param menu
     */
    int insert(Menu menu);

    List<Menu> getList(Map param);

    /**
     * 菜单详情
     * @param id
     */
    Menu getMenu(Integer id);

    /**
     * 菜单更新
     */
    int update(Menu menu);

    /**
     * 菜单删除
     */
    int delete(Menu menu);
}