package cn.bjjoy.service.auth.service;

import cn.bjjoy.service.auth.entity.Area;

import java.util.List;

/**
 * Created by bjjoy on 2017/11/02.
 */
public interface AreaService {

    /**
     * 根据id列表获取地区信息
     */
    List<Area> getAreaList(List<Integer> idList);

    /**
     * 根据code获取地区信息
     */
    Area getAreaByCode(String code);

    Integer insert(Area area);
}
