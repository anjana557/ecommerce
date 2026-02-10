package com.anjana.ecommerceProject.handler;

import com.anjana.ecommerceProject.entity.AuditLog;
import com.anjana.ecommerceProject.entity.Order;
import com.anjana.ecommerceProject.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class AuditLogHandler {
    @Autowired
    private AuditLogRepository auditLogRepository;

    // log audit details (runs in an independent transaction)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAuditDetails(Order order, String action){
        AuditLog auditLog = new AuditLog();
        auditLog.setOrderId(Long.valueOf(order.getId()));
        auditLog.setAction(action);
        auditLog.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(auditLog);
    }
}
