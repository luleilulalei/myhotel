package org.ysu.controller;

import org.ysu.annotation.Autowired;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.pojo.Customer;
import org.ysu.pojo.Goods;
import org.ysu.pojo.GoodsType;
import org.ysu.service.ICustomerService;
import org.ysu.service.IGoodsService;
import org.ysu.service.IGoodsTypeService;

import java.util.List;

@RestController("goods")
public class GoodsController {
    @Autowired
    IGoodsService goodsService;

    @Post("list")
    public List<Goods> list(@RequestParam("goodsTypeName") String goodsTypeName){
        return goodsService.getList(goodsTypeName);
    }

    @Post("get")
    public Goods get(@RequestParam("goodsId") Integer goodsId){
        return goodsService.getById(goodsId);
    }

    @Post("update")
    public Boolean update(Goods goods){
        return goodsService.updateById(goods);
    }

    @Post("delete")
    public Boolean delete(@RequestParam("goodsId")Integer goodsId){
        return goodsService.deleteById(goodsId);
    }

    @Post("add")
    public Boolean add(Goods goods){
        return goodsService.add(goods);
    }
}
