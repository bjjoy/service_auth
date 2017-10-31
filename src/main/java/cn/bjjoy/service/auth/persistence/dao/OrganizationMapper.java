package cn.bjjoy.service.auth.persistence.dao;

import cn.bjjoy.service.auth.entity.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrganizationMapper {
    int deleteById(Integer id);

    int insertOrganization(Organization record);

    Organization getById(Integer id);

    int updateById(Organization record);

    List<Organization> getAll();

    List<Organization> getChildrenByCode(Map<String, String> param);

    Organization getByCode(String code);

    List<Organization> getChildList(@Param("parentId") Integer parentId);

    void updateByParentId(Organization office);

    List<Organization> getListByCode(@Param("code") String code);

}