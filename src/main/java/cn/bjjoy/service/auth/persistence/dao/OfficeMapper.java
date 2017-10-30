package cn.bjjoy.service.auth.persistence.dao;

import cn.bjjoy.service.auth.entity.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OfficeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    List<Organization> selectAll();

    List<Organization> selectChildrenByCode(Map<String, String> param);

    Organization selectByCode(String code);

    List<Organization> getChildOfficeList(@Param("parentId") Integer parentId);

    void updateByParentId(Organization office);

    List<Organization> getListByCode(@Param("code") String code);

}