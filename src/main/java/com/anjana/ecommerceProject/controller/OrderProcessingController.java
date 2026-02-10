package com.anjana.ecommerceProject.controller;

import com.anjana.ecommerceProject.entity.Order;
import com.anjana.ecommerceProject.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderProcessingController {
    @Autowired
    private  final OrderProcessingService orderProcessingService;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderProcessingService.placeAnOrder(order));
    }


}
