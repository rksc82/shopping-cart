package com.rks.service;

import com.rks.model.Product;
import com.rks.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void findAllTest() {

        Product product = new Product(12, "Test", "Description", 24d, 12);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product));
        List<Product> products = productService.findAll();

        assert(products.size() == 1);
        assert(products.contains(product));
    }
}
