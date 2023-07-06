package org.ysu.controller;

import org.ysu.annotation.Autowired;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.pojo.Floor;
import org.ysu.pojo.Room;
import org.ysu.service.IFloorService;
import org.ysu.service.IRoomService;

import java.util.List;

/**
 * 客房编号查询   like
 * 客房类型 ：获取所有的客房类型
 * 状态：
 * 楼层：  查询
 *
 *
 * 添加的时候：
 * 状态  默认 是 空房
 * 楼层是选出来的
 * 房间类型
 *
 * 修改的时候
 * 只能修改空房
 */
@RestController("room")
public class RoomController {
    @Autowired
    IRoomService roomService;

    @Post("list")
    public List<Room> list(
            @RequestParam("roomId") Integer roomId,
            @RequestParam("roomType")String roomType,
            @RequestParam("state")Integer state,
            @RequestParam("floorName")String floorName,
            @RequestParam("roomNumber") String roomNumber){
        return roomService.getList(roomId, roomType, state, floorName, roomNumber);
    }

    @Post("get")
    public Room get(@RequestParam("roomId")Integer roomId, @RequestParam("roomNumber") String roomNumber){
        return roomService.getById(roomId, roomNumber);
    }

    @Post("update")
    public Boolean update(Room room){
        return roomService.updateById(room);
    }

    @Post("delete")
    public Boolean delete(@RequestParam("roomId")Integer roomId){
        return roomService.deleteById(roomId);
    }

    @Post("add")
    public Boolean add(Room room){
        room.setState(2);
        return roomService.add(room);
    }
}
