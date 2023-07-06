package org.ysu.service;

import org.ysu.pojo.Room;
import org.ysu.pojo.User;

import java.util.List;

public interface IUserService {
    List<User> getList(Integer userId, String userName);
    Boolean updateById(User user);
    User getById(Integer userId);
    Boolean deleteById(Integer userId);
    Boolean add(User user);
}
