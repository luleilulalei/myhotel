package org.ysu.service;

import org.ysu.pojo.Floor;
import org.ysu.pojo.Roomtype;

import java.util.List;

public interface IFloorService {
    List<Floor> getList();
    Boolean updateById(Floor floor);
    Floor getById(Integer floorId);
    Boolean deleteById(Integer floorId);
    Boolean add(Floor floor);
}
