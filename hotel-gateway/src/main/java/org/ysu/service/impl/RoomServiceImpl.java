package org.ysu.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.annotation.Service;
import org.ysu.dao.RoomMapper;
import org.ysu.dbutils.MapperUtil;
import org.ysu.dbutils.Transaction;
import org.ysu.pojo.Room;
import org.ysu.pojo.RoomExample;
import org.ysu.service.IRoomService;

import java.util.List;

@Service
public class RoomServiceImpl implements IRoomService {
    @Mapper
    RoomMapper roomMapper;

    @Override
    public List<Room> getList(Integer roomId, String roomType, Integer state, String floorName, String roomNumber) {
        RoomExample roomExample = new RoomExample();
        RoomExample.Criteria criteria = roomExample.createCriteria();
        if(roomId != null){
            criteria.andIdEqualTo(roomId);
        }
        if(!StringUtils.isEmpty(roomType)){
            criteria.andTypeEqualTo(roomType);
        }
        if(state != null){
            criteria.andStateEqualTo(state);

        }
        if(!StringUtils.isEmpty(floorName)){
            criteria.andFloorNameEqualTo(floorName);
        }
        if(!StringUtils.isEmpty(roomNumber)){
            criteria.andRoomNumberEqualTo(roomNumber);
        }
        return roomMapper.selectByExample(roomExample);
    }

    @Override
    @Transaction
    public Boolean updateById(Room room) {
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andIdEqualTo(room.getId());
        roomMapper.updateByExampleSelective(room, roomExample);
        return true;
    }

    @Override
    public Room getById(Integer roomId, String roomNumber) {
        RoomExample roomExample = new RoomExample();
        if(roomId != null){
            roomExample.createCriteria().andIdEqualTo(roomId);
        }else if(!StringUtils.isEmpty(roomNumber)){
            roomExample.createCriteria().andRoomNumberEqualTo(roomNumber);
        }else {
            return null;
        }
        List<Room> rooms = roomMapper.selectByExample(roomExample);
        return rooms.size()>0?rooms.get(0):null;
    }

    @Override
    @Transaction
    public Boolean deleteById(Integer roomId) {
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria().andIdEqualTo(roomId);
        return roomMapper.deleteByExample(roomExample) > 0;
    }

    @Override
    @Transaction
    public Boolean add(Room room) {
        return roomMapper.insertSelective(room)>0;
    }
}
