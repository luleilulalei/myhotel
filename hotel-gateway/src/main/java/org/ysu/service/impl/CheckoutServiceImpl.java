package org.ysu.service.impl;

import org.apache.ibatis.annotations.Mapper;
import org.ysu.annotation.Service;

import org.ysu.dao.CheckoutMapper;
import org.ysu.dbutils.Transaction;
import org.ysu.pojo.Checkout;
import org.ysu.pojo.CheckoutExample;
import org.ysu.service.ICheckoutService;

import java.util.List;

@Service
public class CheckoutServiceImpl implements ICheckoutService {
    @Mapper
    CheckoutMapper checkoutMapper;

    @Override
    public List<Checkout> getList() {
        return checkoutMapper.selectByExample(null);
    }

    @Override
    @Transaction
    public Boolean updateById(Checkout checkout) {
        CheckoutExample checkoutExample = new CheckoutExample();
        checkoutExample.createCriteria().andIdEqualTo(checkout.getId());
        return checkoutMapper.updateByExampleSelective(checkout, checkoutExample) > 0;
    }

    @Override
    public Checkout getById(Integer checkoutId) {
        CheckoutExample checkoutExample = new CheckoutExample();
        checkoutExample.createCriteria().andIdEqualTo(checkoutId);
        List<Checkout> checkouts = checkoutMapper.selectByExample(checkoutExample);
        return checkouts.size() > 0 ? checkouts.get(0) : null;
    }

    @Override
    @Transaction
    public Boolean deleteById(Integer checkoutId) {
        CheckoutExample checkoutExample = new CheckoutExample();
        checkoutExample.createCriteria().andIdEqualTo(checkoutId);
        return checkoutMapper.deleteByExample(checkoutExample) > 0;
    }

    @Override
    @Transaction
    public Boolean add(Checkout checkout) {
        return checkoutMapper.insertSelective(checkout) > 0;
    }
}
