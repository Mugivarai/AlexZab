package ru.AlexZab.tgBot.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.AlexZab.tgBot.repository.ProductRepository;
import ru.AlexZab.tgBot.entity.Product;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsByCategoryId(Long id){
        return productRepository.getProductsByCategoryId(id);
    }

    public List<Product> getProductsByName(String name){
        return productRepository.getProductsByName(name);
    }

     public List<Product> getPopularProducts(Integer limit){
        return productRepository.getPopularProducts(limit);
    }

}
