package org.ysu.service;

import org.ysu.pojo.Goods;
import org.ysu.pojo.GoodsType;

import java.util.List;

public interface IGoodsService {
    List<Goods> getList(String goodsTypeName);
    Boolean updateById(Goods goods);
    Goods getById(Integer goodsId);
    Boolean deleteById(Integer goodsId);
    Boolean add(Goods goods);

}
