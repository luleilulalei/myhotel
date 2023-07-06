package org.ysu.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.annotation.Service;
import org.ysu.dao.UserMapper;
import org.ysu.dbutils.Transaction;
import org.ysu.pojo.User;
import org.ysu.pojo.UserExample;
import org.ysu.service.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Mapper
    UserMapper userMapper;


    @Override
    public List<User> getList(Integer userId, String userName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(userId != null){
            criteria.andIdEqualTo(userId);
        }
        if(!StringUtils.isEmpty(userName)){
            criteria.andNameEqualTo(userName);
        }
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    @Transaction
    public Boolean updateById(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(user.getId());
        return userMapper.updateByExampleSelective(user, userExample)>0;
    }

    @Override
    public User getById(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        return users.size()>0?users.get(0):null;
    }

    @Override
    @Transaction
    public Boolean deleteById(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(userId);
        return userMapper.deleteByExample(userExample)>0;
    }

    @Override
    @Transaction
    public Boolean add(User user) {
        return userMapper.insertSelective(user)>0;
    }
}
