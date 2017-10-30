package cn.bjjoy.service.auth.persistence.dao;

import cn.bjjoy.service.auth.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查询用户的菜单和按钮权限
     *
     * @param param
     * @return
     */
//    List<String> queryRoleByUser(GetRoleDto param);

    List<Role> getList(Map param);

    int getCount(Map param);
}