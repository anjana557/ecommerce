package com.anjana.ecommerceProject.repository;

import com.anjana.ecommerceProject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
