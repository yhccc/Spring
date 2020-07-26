package com.yhc.proxy;

public class OrderServiceProxy implements IOrderService {

    OrderServiceImpl orderService = new OrderServiceImpl();

    @Override
    public void showOrder() {
        System.out.println("---log---");
        orderService.showOrder();
    }
}
