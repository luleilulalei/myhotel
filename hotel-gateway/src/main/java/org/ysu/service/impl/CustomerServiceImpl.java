package org.ysu.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.ysu.annotation.Service;
import org.ysu.dao.CustomerMapper;
import org.ysu.dbutils.Transaction;
import org.ysu.pojo.Customer;
import org.ysu.pojo.CustomerExample;
import org.ysu.service.ICustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Mapper
    CustomerMapper customerMapper;

    @Override
    public List<Customer> getList(Integer customId, String customName) {
        List<Customer> result = null;
        CustomerExample customerExample = new CustomerExample();
        CustomerExample.Criteria criteria = customerExample.createCriteria();
        if(!StringUtils.isEmpty(customName)){
            criteria.andNameEqualTo(customName);
        }
        if(customId != null){
            criteria.andIdEqualTo(customId);
        }
        return customerMapper.selectByExample(customerExample);
    }

    @Override
    @Transaction
    public Boolean updateById(Customer customer) {
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andIdEqualTo(customer.getId());
        return customerMapper.updateByExampleSelective(customer, customerExample) > 0;
    }

    @Override
    public Customer getById(Integer customerId) {
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andIdEqualTo(customerId);
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        return customers.size()>0?customers.get(0):null;
    }

    @Override
    @Transaction
    public Boolean deleteById(Integer customerId) {
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andIdEqualTo(customerId);
        return customerMapper.deleteByExample(customerExample) > 0;
    }

    @Override
    @Transaction
    public Boolean add(Customer customer) {
        return customerMapper.insertSelective(customer)>0;
    }
}
