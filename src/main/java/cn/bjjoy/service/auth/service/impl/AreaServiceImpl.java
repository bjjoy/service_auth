package cn.bjjoy.service.auth.service.impl;

import cn.bjjoy.service.auth.entity.Area;
import cn.bjjoy.service.auth.persistence.dao.AreaMapper;
import cn.bjjoy.service.auth.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gxm on 2017/11/02
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaMapper areaMapper;

    @Override
    public List<Area> getAreaList(List<Integer> idList){
        return areaMapper.getAreaList(idList);
    }

    @Override
    public Area getAreaByCode(String code){
        return areaMapper.getAreaByCode(code);
    }

    @Override
    public Integer insert(Area area){
        return areaMapper.insertArea(area);
    }
}
