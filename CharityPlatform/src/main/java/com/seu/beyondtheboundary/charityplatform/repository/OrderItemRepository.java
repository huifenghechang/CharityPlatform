package com.seu.beyondtheboundary.charityplatform.repository;


import com.seu.beyondtheboundary.charityplatform.domain.OrderItem;
import com.seu.beyondtheboundary.charityplatform.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户仓库.
 *
 * @since 1.0.0 2017年3月2日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public OrderItem findByOrderID(String orderID);
}
