package cn.bjjoy.service.auth.service;

import cn.bjjoy.service.auth.entity.Organization;

import java.util.List;

/**
 * Created by bjjoy on 2017/11/02.
 */
public interface OrganizationService {

    /**
     * 获取机构列表
     *
     * @param code 父级机构编码（null获取所有）
     * @param name 机构名称（null获取所有）
     * @return
     */
    List<Organization> getList(String code, String name);

    /**
     * 根据code获取机构
     * @param code 机构编码
     * @return
     */
    Organization getByCode(String code);

    /**
     * 获取机构名称
     * @param codeList 机构编码列表
     * @return
     */
    List<Organization> getOrganizationName(List<String> codeList);

    /**
     * 更新机构
     * @param organization 机构信息
     * @return
     */
    int update(Organization organization);

    /**
     * 新增机构
     * @param organization 机构信息
     * @return
     */
    int insert(Organization organization);

    /**
     * 根据parentId获取子机构
     * @param parentId
     * @return
     */
    List<Organization> getChildList(Integer parentId);

    /**
     * 根据id获取机构
     * @param id
     * @return
     */
    Organization getById(Integer id);

    /**
     * 删除机构及其子机构
     * @param organization 机构信息
     */
    void deleteOrganization(Organization organization);


    List<Organization> getListByCode(String code);
}
