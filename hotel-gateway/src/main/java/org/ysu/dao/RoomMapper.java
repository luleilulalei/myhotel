package org.ysu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ysu.pojo.Room;
import org.ysu.pojo.RoomExample;

public interface RoomMapper {
    long countByExample(RoomExample example);

    int deleteByExample(RoomExample example);

    int insert(Room record);

    int insertSelective(Room record);

    List<Room> selectByExample(RoomExample example);

    int updateByExampleSelective(@Param("record") Room record, @Param("example") RoomExample example);

    int updateByExample(@Param("record") Room record, @Param("example") RoomExample example);
}