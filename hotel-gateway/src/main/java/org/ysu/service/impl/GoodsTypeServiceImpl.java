package org.ysu.service.impl;

import org.apache.ibatis.annotations.Mapper;
import org.ysu.annotation.Service;
import org.ysu.dao.GoodsTypeMapper;
import org.ysu.dbutils.Transaction;
import org.ysu.pojo.GoodsType;
import org.ysu.pojo.GoodsTypeExample;
import org.ysu.service.IGoodsTypeService;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService {

    @Mapper
    GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> getList() {
        return goodsTypeMapper.selectByExample(null);
    }

    @Override
    @Transaction
    public Boolean updateById(GoodsType goodsType) {
        GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
        goodsTypeExample.createCriteria().andIdEqualTo(goodsType.getId());
        return goodsTypeMapper.updateByExampleSelective(goodsType, goodsTypeExample) > 0;
    }

    @Override
    public GoodsType getById(Integer goodsTypeId) {
        GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
        goodsTypeExample.createCriteria().andIdEqualTo(goodsTypeId);
        List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(goodsTypeExample);
        return goodsTypes.size()>0?goodsTypes.get(0):null;
    }

    @Override
    @Transaction
    public Boolean deleteById(Integer goodsTypeId) {
        GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
        goodsTypeExample.createCriteria().andIdEqualTo(goodsTypeId);
        return goodsTypeMapper.deleteByExample(goodsTypeExample) > 0;
    }

    @Override
    @Transaction
    public Boolean add(GoodsType goodsType) {
        return goodsTypeMapper.insertSelective(goodsType)>0;
    }
}
