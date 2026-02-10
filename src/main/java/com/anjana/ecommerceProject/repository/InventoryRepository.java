package com.anjana.ecommerceProject.repository;

import com.anjana.ecommerceProject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Product, Long> {
}
