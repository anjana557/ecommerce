package com.anjana.ecommerceProject.handler;

import com.anjana.ecommerceProject.entity.AuditLog;
import com.anjana.ecommerceProject.entity.Order;
import com.anjana.ecommerceProject.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentValidatorHandler {
    @Autowired
    private AuditLogRepository auditLogRepository;
    private AuditLogHandler auditLogHandler;

    @Transactional(propagation = Propagation.MANDATORY)
    public void validatePayment(Order order){

        boolean paymentSuccessful = false;

        if (!paymentSuccessful){
            AuditLog paymentFailureLog = new AuditLog();
            paymentFailureLog.setOrderId(Long.valueOf(order.getId()));
            paymentFailureLog.setAction("Payment Failed for order");
            paymentFailureLog.setTimestamp(LocalDateTime.now());

            if (order.getTotalPrice() > 1000){
                //auditLogHandler.logAuditDetails(order, "error in payment validator");
                throw new RuntimeException("error in payment validator");
            }
            //sendOrderConfirmation() to the user



            // save the payment failure log
            //auditLogRepository.save(paymentFailureLog);
        }
    }
}
