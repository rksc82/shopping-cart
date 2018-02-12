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

    //initial loading
    static {
        products.add(new Product(1,"Leather Shoes1","Men shoes used for winter",10.25, 1200));
        products.add(new Product(2,"Leather Shoes2","Men shoes used for winter",10.25, 1200));
        products.add(new Product(3,"Leather Shoes3","Men shoes used for winter",10.25, 1200));
        products.add(new Product(4,"Leather Shoes4","Men shoes used for winter",10.25, 1200));
        products.add(new Product(5,"Leather Shoes5","Men shoes used for winter",10.25, 1200));
        products.add(new Product(6,"Leather Shoes6","Men shoes used for winter",10.25, 1200));
        products.add(new Product(7,"Leather Shoes6","Men shoes used for winter",10.25, 1200));
        products.add(new Product(8,"Leather Shoes7","Men shoes used for winter",10.25, 1200 ));
    }

    public void saveInitialBatch(){
        productRepository.save(products);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
