package com.seu.beyondtheboundary.charityplatform.repository;


import com.seu.beyondtheboundary.charityplatform.domain.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public OrderItem findById(Long id);

    public OrderItem findByOrderId(String orderId);

    public List<OrderItem> findAllByStatus(Long status);

    public List<OrderItem> findAllByRefundStatusAndStatus(Long refund_status, Long status);

}
