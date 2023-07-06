package org.ysu.service;

import org.apache.ibatis.annotations.Mapper;
import org.ysu.pojo.Roomtype;

import java.util.List;

public interface IRoomTypeService {
    List<Roomtype> getList();
    Boolean updateById(Roomtype roomtype);
    Roomtype getById(Integer roomTypeId);
    Boolean deleteById(Integer roomTypeId);
    Boolean add(Roomtype roomtype);
}
