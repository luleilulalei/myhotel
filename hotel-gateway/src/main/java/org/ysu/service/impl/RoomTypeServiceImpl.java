package org.ysu.service.impl;

import org.apache.ibatis.annotations.Mapper;
import org.ysu.annotation.Service;
import org.ysu.dao.RoomtypeMapper;
import org.ysu.dbutils.Transaction;
import org.ysu.pojo.Roomtype;
import org.ysu.pojo.RoomtypeExample;
import org.ysu.service.IRoomTypeService;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements IRoomTypeService {
    @Mapper
    RoomtypeMapper roomtypeMapper;

    @Override
    public List<Roomtype> getList() {
        return roomtypeMapper.selectByExample(null);
    }

    @Override
    @Transaction
    public Boolean updateById(Roomtype roomtype) {
        RoomtypeExample roomtypeExample = new RoomtypeExample();
        roomtypeExample.createCriteria().andIdEqualTo(roomtype.getId());
        int rows = roomtypeMapper.updateByExampleSelective(roomtype, roomtypeExample);
        return rows > 0;
    }

    @Override
    public Roomtype getById(Integer roomTypeId) {
        RoomtypeExample roomtypeExample = new RoomtypeExample();
        roomtypeExample.createCriteria().andIdEqualTo(roomTypeId);
        List<Roomtype> roomtypeList = roomtypeMapper.selectByExample(roomtypeExample);
        return roomtypeList.size() > 0 ? roomtypeList.get(0) : null;
    }

    @Override
    @Transaction
    public Boolean deleteById(Integer roomTypeId) {
        RoomtypeExample roomtypeExample = new RoomtypeExample();
        roomtypeExample.createCriteria().andIdEqualTo(roomTypeId);
        int rows = roomtypeMapper.deleteByExample(roomtypeExample);
        return rows > 0;
    }

    @Override
    @Transaction
    public Boolean add(Roomtype roomtype) {
        return roomtypeMapper.insertSelective(roomtype)>0;
    }
}
