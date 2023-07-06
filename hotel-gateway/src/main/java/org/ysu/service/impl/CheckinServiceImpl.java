package org.ysu.service.impl;

import org.apache.ibatis.annotations.Mapper;
import org.ysu.annotation.Service;
import org.ysu.dao.CheckinMapper;
import org.ysu.dbutils.Transaction;
import org.ysu.entity.dto.CheckInWithRoomTypeDto;
import org.ysu.pojo.Checkin;
import org.ysu.pojo.CheckinExample;
import org.ysu.service.ICheckinService;

import java.util.List;

@Service
public class CheckinServiceImpl implements ICheckinService {
    @Mapper
    CheckinMapper checkinMapper;

    @Override
    public List<Checkin> getList() {
        return checkinMapper.selectByExample(null);
    }

    @Override
    public List<CheckInWithRoomTypeDto> getListShow() {
        List<CheckInWithRoomTypeDto> checkInWithRoomTypeDtos = checkinMapper.selectList();
        checkInWithRoomTypeDtos.forEach(System.out::println);
        return checkinMapper.selectList();
    }

    @Override
    @Transaction
    public Boolean updateById(Checkin checkin) {
        CheckinExample checkinExample = new CheckinExample();
        checkinExample.createCriteria().andIdEqualTo(checkin.getId());
        return checkinMapper.updateByExampleSelective(checkin, checkinExample) > 0;
    }

    @Override
    public Checkin getById(Integer checkinId) {
        CheckinExample checkinExample = new CheckinExample();
        checkinExample.createCriteria().andIdEqualTo(checkinId);
        List<Checkin> checkins = checkinMapper.selectByExample(checkinExample);
        return checkins.size()>0?checkins.get(0):null;
    }

    @Override
    @Transaction
    public Boolean deleteById(Integer checkinId) {
        CheckinExample checkinExample = new CheckinExample();
        checkinExample.createCriteria().andIdEqualTo(checkinId);
        return checkinMapper.deleteByExample(checkinExample) > 0;
    }

    @Override
    @Transaction
    public Boolean add(Checkin checkin) {
        return checkinMapper.insertSelective(checkin) > 0;
    }
}
