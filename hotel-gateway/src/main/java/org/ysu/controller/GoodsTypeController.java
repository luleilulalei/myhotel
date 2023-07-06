package org.ysu.controller;

import org.ysu.annotation.Autowired;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.pojo.GoodsType;
import org.ysu.service.IGoodsTypeService;

import java.util.List;

@RestController("goodsType")
public class GoodsTypeController {
    @Autowired
    IGoodsTypeService goodsTypeService;

    @Post("list")
    public List<GoodsType> list(){
        return goodsTypeService.getList();
    }

    @Post("get")
    public GoodsType get(@RequestParam("goodsTypeId") Integer goodsTypeId){
        return goodsTypeService.getById(goodsTypeId);
    }

    @Post("update")
    public Boolean update(GoodsType goodsType){
        return goodsTypeService.updateById(goodsType);
    }

    @Post("delete")
    public Boolean delete(@RequestParam("goodsTypeId")Integer goodsTypeId){
        return goodsTypeService.deleteById(goodsTypeId);
    }

    @Post("add")
    public Boolean add(GoodsType goodsType){
        return goodsTypeService.add(goodsType);
    }
}
