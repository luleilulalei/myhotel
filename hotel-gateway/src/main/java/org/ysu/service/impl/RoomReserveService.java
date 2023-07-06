package org.ysu.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.annotation.Service;
import org.ysu.dao.CustomerMapper;
import org.ysu.dao.RoomReserveMapper;
import org.ysu.dbutils.Transaction;
import org.ysu.entity.dto.RoomReserveWithRoomTypeNameDto;
import org.ysu.pojo.Customer;
import org.ysu.pojo.CustomerExample;
import org.ysu.pojo.RoomReserve;
import org.ysu.pojo.RoomReserveExample;
import org.ysu.service.IRoomReserveService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomReserveService implements IRoomReserveService {
    @Mapper
    RoomReserveMapper roomReserveMapper;
    @Mapper
    CustomerMapper customerMapper;

    @Override
    public List<RoomReserve> getList(String no, Integer roomTypeId, Integer state, String roomNumber, String customerName) {
        RoomReserveExample roomReserveExample = new RoomReserveExample();
        RoomReserveExample.Criteria criteria = roomReserveExample.createCriteria();
        if(!StringUtils.isEmpty(no)){
            criteria.andNoEqualTo(no);
        }
        if (roomTypeId != null) {
            criteria.andRoomTypeIdEqualTo(roomTypeId);
        }
        if (state != null) {
            criteria.andStateEqualTo(state);
        }
        if (!StringUtils.isEmpty(roomNumber)) {
            criteria.andRoomNumberEqualTo(roomNumber);
        }
        if(!StringUtils.isEmpty(customerName)){
            CustomerExample customerExample = new CustomerExample();
            customerExample.createCriteria().andNameEqualTo(customerName);
            List<Customer> customers = customerMapper.selectByExample(customerExample);
            if(customers.size() == 0){
                return new ArrayList<>();
            }else {
                List<Integer> customerIds = new ArrayList<>();
                customers.forEach(item -> customerIds.add(item.getId()));
                criteria.andCustomerIdIn(customerIds);
            }
        }
        return roomReserveMapper.selectByExample(roomReserveExample);
    }

    @Override
    @Transaction
    public Boolean updateById(RoomReserve roomReserve) {
        RoomReserveExample roomReserveExample = new RoomReserveExample();
        roomReserveExample.createCriteria().andIdEqualTo(roomReserve.getId());
        return roomReserveMapper.updateByExampleSelective(roomReserve, roomReserveExample) > 0;
    }

    @Override
    public RoomReserve getById(Integer roomReserveId) {
        RoomReserveExample roomReserveExample = new RoomReserveExample();
        roomReserveExample.createCriteria().andIdEqualTo(roomReserveId);
        List<RoomReserve> roomReserves = roomReserveMapper.selectByExample(roomReserveExample);
        return roomReserves.size()>0?roomReserves.get(0):null;
    }

    @Override
    @Transaction
    public Boolean deleteById(Integer roomReserveId) {
        RoomReserveExample roomReserveExample = new RoomReserveExample();
        roomReserveExample.createCriteria().andIdEqualTo(roomReserveId);
        return roomReserveMapper.deleteByExample(roomReserveExample)>0;
    }

    @Override
    @Transaction
    public Boolean add(RoomReserve roomReserve) {
        return roomReserveMapper.insertSelective(roomReserve) > 0;
    }

    @Override
    public RoomReserveWithRoomTypeNameDto getByIdWithRoomTypeName(Integer roomTypeId) {
        return roomReserveMapper.getByIdWithRoomTypeName(roomTypeId);
    }
}
