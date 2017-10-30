package cn.bjjoy.service.auth.persistence.dao;

import cn.bjjoy.service.auth.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AreaMapper {
    int deleteById(Integer id);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    /**
     * 根据id列表获取地区信息
     */
    List<Area> getAreaList(@Param("idList") List<Integer> idList);

    /**
     * 根据code获取地区信息
     */
    Area getAreaByCode(@Param("code") String code);

}