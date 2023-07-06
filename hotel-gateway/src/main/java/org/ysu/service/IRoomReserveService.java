package org.ysu.service;

import org.ysu.annotation.RequestParam;
import org.ysu.entity.dto.RoomReserveWithRoomTypeNameDto;
import org.ysu.pojo.Room;
import org.ysu.pojo.RoomReserve;

import java.util.List;

public interface IRoomReserveService {
    List<RoomReserve> getList(String no, Integer roomTypeId, Integer state, String roomNumber, String customerName);
    Boolean updateById(RoomReserve roomReserve);
    RoomReserve getById(Integer roomReserveId);
    Boolean deleteById(Integer roomReserveId);
    Boolean add(RoomReserve roomReserve);
    RoomReserveWithRoomTypeNameDto getByIdWithRoomTypeName(Integer roomTypeId);
}
