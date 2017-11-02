package cn.bjjoy.service.auth.service.impl;

import cn.bjjoy.service.auth.entity.Organization;
import cn.bjjoy.service.auth.persistence.dao.OrganizationMapper;
import cn.bjjoy.service.auth.service.OrganizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gxm on 2017/11/03.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<Organization> getList(String code, String name){

        if (StringUtils.isBlank(code) && StringUtils.isBlank(name)) {
            return organizationMapper.getAll();
        } else {
            Map<String, String> param = new HashMap<>();
            param.put("code", code);
            param.put("name", name);
            return organizationMapper.getChildrenByCode(param);
        }
    }

    @Override
    public Organization getByCode(String code){
        Organization organization = organizationMapper.getByCode(code);
        if (null == organization) {
//            throw new BusinessException(-1,"ERROR:code:"+code+" 没有匹配到一条组织机构！" );
        }

        return organization;
    }

    @Override
    public List<Organization> getOrganizationName(List<String> codeList){
        List<Organization> organizationList = new ArrayList<>();
        for (String code : codeList) {
            Organization organization = organizationMapper.getByCode(code);
            if (null != organization) {
                organizationList.add(organization);
            }
        }
        return organizationList;
    }

    @Override
    public int update(Organization organization){
        return organizationMapper.updateById(organization);
    }

    @Override
    public int insert(Organization organization){
        organization.setCode(this.getOrganizationCode(organization.getParentId()));
        return organizationMapper.insertOrganization(organization);
    }

   @Override
   public List<Organization> getChildList(Integer parentId){
        return organizationMapper.getChildList(parentId);
   }

   @Override
   public Organization getById(Integer id){
       return organizationMapper.getById(id);
   }

   @Override
   public void deleteOrganization(Organization organization){
       organizationMapper.updateById(organization);
       organizationMapper.updateByParentId(organization);
   }

    /**
     * 生成同一机构下新的机构编码（同级机构编码+1）
     * @param parentId
     * @return
     */
    private String getOrganizationCode(Integer parentId){
        List<Organization> organizationList = this.getChildList(parentId);
        if(organizationList.size() == 0){
            Organization organization = this.getById(parentId);
            return organization.getCode() + "001";
        }
        String resultCode = organizationList.get(0).getCode();
        int maxCode = Integer.valueOf(resultCode.substring(resultCode.length()-3, resultCode.length()));
        for(Organization organization : organizationList){
            String code = organization.getCode();
            int tmpCode = Integer.valueOf(code.substring(code.length()-3, code.length()));
            if(maxCode < tmpCode){
                maxCode = tmpCode;
            }
        }
        int newCode = maxCode+1;
        int newCodeLen = String.valueOf(newCode).length();
        String tmpResultCode = resultCode.substring(0,resultCode.length()-3);
        for(int i = 0; i < 3 - newCodeLen; i++){
            tmpResultCode += "0";
        }
        return tmpResultCode + String.valueOf(newCode);
    }

    /**
     * 获取code及code一下单位集合（按照 code和length（code） 排序）
     * @param code
     * @return
     */
    public List<Organization> getListByCode(String code){
        return organizationMapper.getListByCode(code);
    }
}
