package org.ysu.controller;

import org.ysu.annotation.Autowired;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.entity.dto.RoomReserveWithRoomTypeNameDto;
import org.ysu.pojo.RoomReserve;
import org.ysu.pojo.Roomtype;
import org.ysu.service.IRoomReserveService;
import org.ysu.service.IRoomTypeService;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 预定  预定单号后端生成
 *
 */
@RestController("roomReserve")
public class RoomReserveController {
    @Autowired
    IRoomReserveService roomReserveService;

    @Post("list")
    public List<RoomReserve> list(
            @RequestParam("no") String no,
            @RequestParam("roomTypeId") Integer roomTypeId,
            @RequestParam("state") Integer state,
            @RequestParam("roomNumber") String roomNumber,
            @RequestParam("customerName") String customerName
    ){
        return roomReserveService.getList(no, roomTypeId, state, roomNumber, customerName);
    }

    @Post("get")
    public RoomReserve get(@RequestParam("roomReserveId")Integer roomReserveId){
        return roomReserveService.getById(roomReserveId);
    }

    @Post("getWithRoomTypeName")
    public RoomReserveWithRoomTypeNameDto getWithRoomTypeName(@RequestParam("roomReserveId")Integer roomReserveId){
        return roomReserveService.getByIdWithRoomTypeName(roomReserveId);
    }

    @Post("update")
    public Boolean update(RoomReserve roomReserve){
        return roomReserveService.updateById(roomReserve);
    }

    @Post("delete")
    public Boolean delete(@RequestParam("roomReserveId")Integer roomReserveId){
        return roomReserveService.deleteById(roomReserveId);
    }

    @Post("add")
    public String add(RoomReserve roomReserve){
        String no;
        synchronized (RoomReserveController.class){
            no = String.valueOf(System.currentTimeMillis());
            roomReserve.setNo(no);
            roomReserve.setUserId(0);
        }
        return roomReserveService.add(roomReserve)?no:null;
    }
}
