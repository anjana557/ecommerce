package com.anjana.ecommerceProject.handler;

import com.anjana.ecommerceProject.entity.Order;
import com.anjana.ecommerceProject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandler {
    @Autowired
    private final OrderRepository orderRepository;

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
}
