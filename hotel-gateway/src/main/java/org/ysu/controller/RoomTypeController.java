package org.ysu.controller;

import org.ysu.annotation.Autowired;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.pojo.Roomtype;
import org.ysu.service.IRoomTypeService;

import java.util.List;

@RestController("roomType")
public class RoomTypeController {
    @Autowired
    IRoomTypeService roomTypeService;

    @Post("list")
    public List<Roomtype> list(){
        return roomTypeService.getList();
    }

    @Post("get")
    public Roomtype get(@RequestParam("roomTypeId")Integer roomTypeId){
        return roomTypeService.getById(roomTypeId);
    }

    @Post("update")
    public Boolean update(Roomtype roomtype){
        return roomTypeService.updateById(roomtype);
    }

    @Post("delete")
    public Boolean delete(@RequestParam("roomTypeId")Integer roomTypeId){
        return roomTypeService.deleteById(roomTypeId);
    }

    @Post("add")
    public Boolean add(Roomtype roomtype){
        return roomTypeService.add(roomtype);
    }
}
