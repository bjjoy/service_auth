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
    int deleteByPrimaryKey(String uuid);

    int insert(Map param);

    int insertSelective(Map param);

    User selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Map param);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户登录查询用户信息
     *
     * @param loginName
     * @return
     */
    List<User> selectByLoginName(String loginName);

    List<Map> getList(Map param);

    Integer getCount(Map param);

    Map getUserDetail(@Param("uuid") String uuid);
}