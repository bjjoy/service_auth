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
    int deleteById(Integer id);

    int insertRole(Role record);

    Role getById(Integer id);

    int updateById(Role record);

    List<Role> getList(Map param);

    int getCount(Map param);
}