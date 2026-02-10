package com.anjana.ecommerceProject.service;

import com.anjana.ecommerceProject.entity.Order;
import com.anjana.ecommerceProject.entity.Product;
import com.anjana.ecommerceProject.handler.AuditLogHandler;
import com.anjana.ecommerceProject.handler.InventoryHandler;
import com.anjana.ecommerceProject.handler.OrderHandler;
import com.anjana.ecommerceProject.handler.PaymentValidatorHandler;
import com.anjana.ecommerceProject.repository.InventoryRepository;
import com.anjana.ecommerceProject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderProcessingService {
    @Autowired
    private final OrderHandler orderHandler;
    @Autowired
    private final InventoryHandler inventoryHandler;

    @Autowired
    private final AuditLogHandler auditLogHandler;

    private final PaymentValidatorHandler paymentValidatorHandler;

    //@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)

    @Transactional(propagation = Propagation.REQUIRED)
    public Order placeAnOrder(Order order){
        // get product from inventory
        Product product = inventoryHandler.getProduct(order.getProductId());

        // validate stock availability < 5
        if(order.getQuantity() > product.getStockQuantity()){
            throw  new RuntimeException("Insufficient stock !");
        }

        // update total price in order entity
        order.setTotalPrice(order.getQuantity()*product.getPrice());

        Order saveOrder = null;
        try {
            // saved order
            saveOrder = orderHandler.saveOrder(order);

            //update stock in inventory
            int availableStock = product.getStockQuantity() - order.getQuantity();
            product.setStockQuantity(availableStock);
            inventoryHandler.updateProductDetails(product);
            auditLogHandler.logAuditDetails(order,"order placement succeeded");
        } catch (Exception e) {
            auditLogHandler.logAuditDetails(order,"order placement failed");

        }

        // validatePayment();
        paymentValidatorHandler.validatePayment(order);

        return saveOrder;

    }
}
