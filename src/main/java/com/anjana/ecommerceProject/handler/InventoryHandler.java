package com.anjana.ecommerceProject.handler;

import com.anjana.ecommerceProject.entity.Product;
import com.anjana.ecommerceProject.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryHandler {
    @Autowired
    private final InventoryRepository inventoryRepository;

    public Product getProduct(Long id){
        return inventoryRepository.findById(id).orElseThrow(()->
               new RuntimeException("Product not found!!!")
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Product updateProductDetails(Product product){
        if (product.getPrice() > 5000){
            throw new RuntimeException("DB crashed.......");
        }
        return inventoryRepository.save(product);
    }
}
