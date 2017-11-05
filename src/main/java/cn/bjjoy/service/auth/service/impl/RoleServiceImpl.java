package cn.bjjoy.service.auth.service.impl;

import cn.bjjoy.service.auth.dto.MenuDto;
import cn.bjjoy.service.auth.dto.RoleDto;
import cn.bjjoy.service.auth.dto.UserRoleDto;
import cn.bjjoy.service.auth.entity.Menu;
import cn.bjjoy.service.auth.entity.Role;
import cn.bjjoy.service.auth.entity.RoleMenu;
import cn.bjjoy.service.auth.persistence.dao.MenuMapper;
import cn.bjjoy.service.auth.persistence.dao.RoleMapper;
import cn.bjjoy.service.auth.persistence.dao.RoleMenuMapper;
import cn.bjjoy.service.auth.persistence.dao.UserRoleMapper;
import cn.bjjoy.service.auth.service.RoleService;
import cn.bjjoy.service.auth.util.DataUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by gxm on 2017/11/02.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

//    @Override
//    public List<String> queryRoleByUser(String userUuid, String permission){
//        UserRoleDto userRoleDto = new UserRoleDto();
//        userRoleDto.setUserUuid(userUuid);
//        userRoleDto.setPermission(permission);
//        return roleMapper.queryRoleByUser(userRoleDto);
//    }

    @Override
    public List<Role> getList(Map param){
        return roleMapper.getList(param);
    }

    @Override
    public int getCount(Map param){
        return roleMapper.getCount(param);
    }

    @Override
    public int create(RoleDto roleDto){
        Role role = DataUtils.getData(roleDto, Role.class);
        //新建角色
        int count = roleMapper.insertRole(role);
        if (StringUtils.isBlank(roleDto.getMenuIds())){
            return count;
        }

        //新建角色菜单关系
        List<RoleMenu> roleMenuList = this.getRoleMenuList(role.getId(), roleDto.getMenuIds());
        count += roleMenuMapper.insertBatch(roleMenuList);
        return count;
    }

    @Override
    public Map getRole(Integer id){
        Role role = roleMapper.getById(id);
        Map resultMap = DataUtils.getData(role, Map.class);

        //获取菜单信息
        Map param = new HashMap();
        List<Menu> menuList = menuMapper.getList(param);
        List<MenuDto> menuDtoList = DataUtils.getDataArray(menuList, MenuDto.class);
        param.put("roleId", id);
        List<RoleMenu> roleMenuList = roleMenuMapper.getList(param);
        for (MenuDto menuVO : menuDtoList){
            for (RoleMenu roleMenu : roleMenuList){
                if (menuVO.getId().equals(roleMenu.getMenuId())){
                    menuVO.setIsSelect(1);
                }
            }
        }
        resultMap.put("menuList", menuDtoList);
        return resultMap;
    }

    @Override
    public int update(RoleDto roleDto){
        Role role = DataUtils.getData(roleDto, Role.class);
        int count = roleMapper.updateById(role);
        if (StringUtils.isBlank(roleDto.getMenuIds())){
            return count;
        }
        count += roleMenuMapper.deleteByRoleId(role.getId());
        List<RoleMenu> roleMenuList = this.getRoleMenuList(role.getId(), roleDto.getMenuIds());
        count += roleMenuMapper.insertBatch(roleMenuList);
        return count;
    }

    @Override
    public int delete(Role role){
        int count = roleMapper.updateById(role);
        count += roleMenuMapper.deleteByRoleId(role.getId());
        return count;
    }

    @Override
    public int getUserRoleCountByRoleId(Integer roleId){
        return userRoleMapper.getUserRoleCountByRoleId(roleId);
    }

    /**
     * 生成role_menu列表
     * @param roleId
     * @param menuIds
     * @return
     */
    private List<RoleMenu> getRoleMenuList(Integer roleId, String menuIds){
        if(StringUtils.isNotEmpty(menuIds)){
            List<RoleMenu> roleMenuList = new ArrayList<>();
            List<String> menuIdList = Arrays.asList(menuIds.split("\\$\\$"));
            for (String menuId : menuIdList){
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(Integer.valueOf(menuId));
                roleMenu.setRoleId(roleId);
                roleMenuList.add(roleMenu);
            }
            return roleMenuList;
        }
        return null;
    }

}