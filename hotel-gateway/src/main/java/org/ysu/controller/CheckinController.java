package org.ysu.controller;

import org.ysu.annotation.Autowired;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.entity.dto.CheckInWithRoomTypeDto;
import org.ysu.pojo.Checkin;
import org.ysu.pojo.Floor;
import org.ysu.service.ICheckinService;

import java.util.List;

@RestController("checkin")
public class CheckinController {
    @Autowired
    ICheckinService checkinService;

    @Post("list")
    public List<Checkin> list() {
        return checkinService.getList();
    }

    @Post("listShow")
    public List<CheckInWithRoomTypeDto> listShow() {
        return checkinService.getListShow();
    }

    @Post("get")
    public Checkin get(@RequestParam("checkinId") Integer checkinId) {
        return checkinService.getById(checkinId);
    }

    @Post("update")
    public Boolean update(Checkin checkin) {
        return checkinService.updateById(checkin);
    }

    @Post("delete")
    public Boolean delete(@RequestParam("checkinId") Integer checkinId) {
        return checkinService.deleteById(checkinId);
    }

    @Post("add")
    public Boolean add(Checkin checkin) {
        return checkinService.add(checkin);
    }
}
