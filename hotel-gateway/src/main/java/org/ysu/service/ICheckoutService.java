package org.ysu.service;

import org.ysu.pojo.Checkout;
import org.ysu.pojo.Customer;

import java.util.List;

public interface ICheckoutService {
    List<Checkout> getList();
    Boolean updateById(Checkout checkout);
    Checkout getById(Integer checkoutId);
    Boolean deleteById(Integer checkoutId);
    Boolean add(Checkout checkout);
}
