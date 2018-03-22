package com.seu.beyondtheboundary.charityplatform.service;

import com.seu.beyondtheboundary.charityplatform.domain.OrderItem;
import com.seu.beyondtheboundary.charityplatform.domain.Project;

import java.util.List;

public  interface OrderItemService {
    /**
     * 保存订单
     * @param
     * @return
     */
    OrderItem saveOrderItemService(OrderItem orderItem);

    /**
     * 删除订单
     * @param
     * @return
     */
    void removeOrderItemService(Long id);


    /**
     * 根据id获取订单
     * @param
     * @return
     */
    OrderItem getOrderItemById(Long id);

}