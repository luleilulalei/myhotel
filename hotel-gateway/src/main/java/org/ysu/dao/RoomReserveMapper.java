package org.ysu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ysu.entity.dto.RoomReserveWithRoomTypeNameDto;
import org.ysu.pojo.RoomReserve;
import org.ysu.pojo.RoomReserveExample;

public interface RoomReserveMapper {
    long countByExample(RoomReserveExample example);

    int deleteByExample(RoomReserveExample example);

    int insert(RoomReserve record);

    int insertSelective(RoomReserve record);

    List<RoomReserve> selectByExample(RoomReserveExample example);

    int updateByExampleSelective(@Param("record") RoomReserve record, @Param("example") RoomReserveExample example);

    int updateByExample(@Param("record") RoomReserve record, @Param("example") RoomReserveExample example);

    RoomReserveWithRoomTypeNameDto getByIdWithRoomTypeName(@Param("roomReserveId") Integer roomReserveId);
}