package com.anjana.ecommerceProject.repository;

import com.anjana.ecommerceProject.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
