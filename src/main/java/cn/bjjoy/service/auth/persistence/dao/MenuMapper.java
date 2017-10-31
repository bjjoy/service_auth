package cn.bjjoy.service.auth.persistence.dao;

import cn.bjjoy.service.auth.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MenuMapper {
    int deleteById(Integer id);

    int insertMenu(Menu record);

    Menu getById(Integer id);

    int updateById(Menu record);

    /**
     * 更新父菜单下所有子菜单信息
     * @param parentMenu
     * @return
     */
    int updateByParentId(Menu parentMenu);

    /**
     * 查询用户可见的菜单项
     *
     * @param userUuid 用户ID
     * @return
     */
//    List<Menu> queryMenuByUser(@Param("userUuid") String userUuid);

    /**
     * 获取菜单列表
     * @param param
     * @return
     */
    List<Menu> getList(Map param);
}