package cn.bjjoy.service.auth.persistence.dao;

import cn.bjjoy.service.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    int deleteByUuid(String uuid);

    int insertUser(Map param);

    User getByUuid(String uuid);

    int updateByUuid(Map param);

    List<User> getList(Map param);

    Integer getCount(Map param);

}