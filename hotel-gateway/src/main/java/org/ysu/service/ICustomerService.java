package org.ysu.service;

import org.ysu.pojo.Customer;
import org.ysu.pojo.Floor;

import java.util.List;

public interface ICustomerService {
    List<Customer> getList(Integer customId, String customName);
    Boolean updateById(Customer customer);
    Customer getById(Integer customerId);
    Boolean deleteById(Integer customerId);
    Boolean add(Customer customer);
}
