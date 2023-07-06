package org.ysu.controller;

import org.ysu.annotation.Autowired;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.pojo.User;
import org.ysu.service.IUserService;

import java.util.List;

/**
 * ID 或 Name 来查询
 */
@RestController("user")
public class UserController {
    @Autowired
    IUserService userService;

    @Post("list")
    public List<User> list(@RequestParam("userId")Integer userId,@RequestParam("userName") String userName){
        return userService.getList(userId, userName);
    }

    @Post("get")
    public User get(@RequestParam("userId")Integer userId){
        return userService.getById(userId);
    }

    @Post("update")
    public Boolean update(User user){
        return userService.updateById(user);
    }

    @Post("delete")
    public Boolean delete(@RequestParam("userId")Integer userId){
        return userService.deleteById(userId);
    }

    @Post("add")
    public Boolean add(User user){
        return userService.add(user);
    }
}
