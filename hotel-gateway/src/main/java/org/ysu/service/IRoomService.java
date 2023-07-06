package org.ysu.service;

import org.ysu.pojo.GoodsType;
import org.ysu.pojo.Room;

import java.util.List;

public interface IRoomService {
    List<Room> getList(Integer roomId, String roomType, Integer state, String floorName, String roomNumber);
    Boolean updateById(Room room);
    Room getById(Integer roomId, String roomNumber);
    Boolean deleteById(Integer roomId);
    Boolean add(Room room);
}
