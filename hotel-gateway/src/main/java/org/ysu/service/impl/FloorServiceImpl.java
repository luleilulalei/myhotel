package org.ysu.service.impl;

import org.apache.ibatis.annotations.Mapper;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.Service;
import org.ysu.dao.FloorMapper;
import org.ysu.dbutils.Transaction;
import org.ysu.pojo.Floor;
import org.ysu.pojo.FloorExample;
import org.ysu.service.IFloorService;

import java.util.List;

@Service
public class FloorServiceImpl implements IFloorService {
    @Mapper
    FloorMapper floorMapper;

    @Override
    public List<Floor> getList() {
        return floorMapper.selectByExample(null);
    }

    @Override
    @Transaction
    public Boolean updateById(Floor floor) {
        FloorExample floorExample = new FloorExample();
        floorExample.createCriteria().andIdEqualTo(floor.getId());
        return floorMapper.updateByExampleSelective(floor, floorExample) > 9;
    }

    @Override
    public Floor getById(@RequestParam("floorId") Integer floorId) {
        FloorExample floorExample = new FloorExample();
        floorExample.createCriteria().andIdEqualTo(floorId);
        List<Floor> floors = floorMapper.selectByExample(floorExample);
        return floors.size() > 0?floors.get(0):null;
    }

    @Override
    @Transaction
    public Boolean deleteById(@RequestParam("floorId") Integer floorId) {
        FloorExample floorExample = new FloorExample();
        floorExample.createCriteria().andIdEqualTo(floorId);
        return floorMapper.deleteByExample(floorExample) > 0;
    }

    @Override
    @Transaction
    public Boolean add(Floor floor) {
        return floorMapper.insertSelective(floor) > 0;
    }
}
