package com.rks.service;

import com.rks.model.Product;
import com.rks.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static List<Product> products = new ArrayList<>();

    //this is only used for initial loading of data to database which should be moved to database scripts
    static {
        products.add(new Product(1, "Leather Shoes", "Men shoes used for winter", 10.25, 100));
        products.add(new Product(2, "Shirt", "Men shirt used for summer", 25.25, 200));
        products.add(new Product(3, "Bag", "Ladies bag used all time", 24.25, 300));
        products.add(new Product(4, "Gloves", "Ladies gloves for winter", 53.25, 500));
        products.add(new Product(5, "Cap", "Men cap used for seasons", 12.25, 400));
        products.add(new Product(6, "Jeans", "Men denim jeans", 10.25, 600));
    }

    public void saveInitialBatch(){
        productRepository.save(products);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
