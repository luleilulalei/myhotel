package org.ysu.controller;

import org.ysu.annotation.Autowired;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.pojo.Customer;
import org.ysu.service.ICustomerService;

import java.util.List;

/**
 * 列表查询需要通过 ID 或者 name 来查询
 */
@RestController("customer")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @Post("list")
    public List<Customer> list(@RequestParam("customerId")Integer customId, @RequestParam("customName")String customName){
        return customerService.getList(customId, customName);
    }

    @Post("get")
    public Customer get(@RequestParam("customerId") Integer customerId){
        return customerService.getById(customerId);
    }

    @Post("update")
    public Boolean update(Customer customer){
        return customerService.updateById(customer);
    }

    @Post("delete")
    public Boolean delete(@RequestParam("customerId")Integer customerId){
        return customerService.deleteById(customerId);
    }

    @Post("add")
    public Boolean add(Customer customer){
        return customerService.add(customer);
    }
}
