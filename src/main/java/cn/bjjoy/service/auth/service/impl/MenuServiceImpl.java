package cn.bjjoy.service.auth.service.impl;

import cn.bjjoy.service.auth.entity.Menu;
import cn.bjjoy.service.auth.persistence.dao.MenuMapper;
import cn.bjjoy.service.auth.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by bjjoy on 2017/11/02.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

//    @Override
//    public List<Menu> queryMenuByUser(String userUuid){
//        return menuMapper.queryMenuByUser(userUuid);
//    }

    @Override
    public int insert(Menu menu){
        return menuMapper.insertMenu(menu);
    }

    @Override
    public List<Menu> getList(Map param){
        return menuMapper.getList(param);
    }

    @Override
    public Menu getMenu(Integer id){
        return menuMapper.getById(id);
    }

    @Override
    public int update(Menu menu){
        return menuMapper.updateById(menu);
    }

    @Override
    public int delete(Menu menu){
        //更新del_flag=1
        int count = menuMapper.updateById(menu);

        //更新子菜单del_flag=1
        count += menuMapper.updateByParentId(menu);

        return count;
    }
}
