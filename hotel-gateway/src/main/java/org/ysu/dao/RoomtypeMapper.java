package org.ysu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ysu.pojo.Roomtype;
import org.ysu.pojo.RoomtypeExample;

public interface RoomtypeMapper {
    long countByExample(RoomtypeExample example);

    int deleteByExample(RoomtypeExample example);

    int insert(Roomtype record);

    int insertSelective(Roomtype record);

    List<Roomtype> selectByExample(RoomtypeExample example);

    int updateByExampleSelective(@Param("record") Roomtype record, @Param("example") RoomtypeExample example);

    int updateByExample(@Param("record") Roomtype record, @Param("example") RoomtypeExample example);
}