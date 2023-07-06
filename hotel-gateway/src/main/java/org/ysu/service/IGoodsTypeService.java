package org.ysu.service;

import org.ysu.pojo.Floor;
import org.ysu.pojo.GoodsType;

import java.util.List;

public interface IGoodsTypeService {
    List<GoodsType> getList();
    Boolean updateById(GoodsType goodsType);
    GoodsType getById(Integer goodsTypeId);
    Boolean deleteById(Integer goodsTypeId);
    Boolean add(GoodsType goodsType);
}
