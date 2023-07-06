package org.ysu.service;

import org.ysu.entity.dto.CheckInWithRoomTypeDto;
import org.ysu.pojo.Checkin;
import org.ysu.pojo.Customer;

import java.util.List;

public interface ICheckinService {
    List<Checkin> getList();
    List<CheckInWithRoomTypeDto> getListShow();
    Boolean updateById(Checkin checkin);
    Checkin getById(Integer checkinId);
    Boolean deleteById(Integer checkinId);
    Boolean add(Checkin checkin);
}
