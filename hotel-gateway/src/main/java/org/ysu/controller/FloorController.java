package org.ysu.controller;

import org.ysu.annotation.Autowired;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.pojo.Floor;
import org.ysu.pojo.Roomtype;
import org.ysu.service.IFloorService;

import java.util.List;

@RestController("floor")
public class FloorController {
    @Autowired
    IFloorService floorService;

    @Post("list")
    public List<Floor> list(){
        return floorService.getList();
    }

    @Post("get")
    public Floor get(@RequestParam("floorId") Integer floorId){
        return floorService.getById(floorId);
    }

    @Post("update")
    public Boolean update(Floor floor){
        return floorService.updateById(floor);
    }

    @Post("delete")
    public Boolean delete(@RequestParam("floorId") Integer floorId){
        return floorService.deleteById(floorId);
    }

    @Post("add")
    public Boolean add(Floor floor){
        return floorService.add(floor);
    }
}
