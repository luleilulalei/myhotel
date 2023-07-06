package org.ysu.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.annotation.Service;
import org.ysu.dao.GoodsMapper;
import org.ysu.dao.GoodsTypeMapper;
import org.ysu.pojo.Goods;
import org.ysu.pojo.GoodsExample;
import org.ysu.pojo.GoodsType;
import org.ysu.pojo.GoodsTypeExample;
import org.ysu.service.IGoodsService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService implements IGoodsService {
    @Mapper
    GoodsTypeMapper goodsTypeMapper;
    @Mapper
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> getList(String goodsTypeName) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        if(!StringUtils.isEmpty(goodsTypeName)){
            GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
            goodsTypeExample.createCriteria().andNameEqualTo(goodsTypeName);
            List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(goodsTypeExample);
            if(goodsTypes.size() == 0){
                return new ArrayList<>();
            }
            criteria.andGoodsTypeIdEqualTo(goodsTypes.get(0).getId());
        }
        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public Boolean updateById(Goods goods) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(goods.getId());
        goods.setId(null);
        return goodsMapper.updateByExampleSelective(goods, goodsExample)>0;
    }

    @Override
    public Goods getById(Integer goodsId) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(goodsId);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods.size() > 0?goods.get(0):null;
    }

    @Override
    public Boolean deleteById(Integer goodsId) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(goodsId);
        return goodsMapper.deleteByExample(goodsExample)>0;
    }

    @Override
    public Boolean add(Goods goods) {
        return goodsMapper.insertSelective(goods)>0;
    }
}
