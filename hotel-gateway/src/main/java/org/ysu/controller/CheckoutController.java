package org.ysu.controller;

import org.ysu.annotation.Autowired;
import org.ysu.annotation.Post;
import org.ysu.annotation.RequestParam;
import org.ysu.annotation.RestController;
import org.ysu.pojo.Checkout;
import org.ysu.service.ICheckoutService;

import java.util.List;

@RestController("checkout")
public class CheckoutController {
    @Autowired
    ICheckoutService checkoutService;

    @Post("list")
    public List<Checkout> list() {
        return checkoutService.getList();
    }

    @Post("get")
    public Checkout get(@RequestParam("checkoutId") Integer checkoutId) {
        return checkoutService.getById(checkoutId);
    }

    @Post("update")
    public Boolean update(Checkout checkout) {
        return checkoutService.updateById(checkout);
    }

    @Post("delete")
    public Boolean delete(@RequestParam("checkoutId") Integer checkoutId) {
        return checkoutService.deleteById(checkoutId);
    }

    @Post("add")
    public Boolean add(Checkout checkout) {
        return checkoutService.add(checkout);
    }
}
