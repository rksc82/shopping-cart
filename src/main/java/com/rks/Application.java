package com.rks;

import com.rks.Service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        ProductService productService = applicationContext.getBean(ProductService.class);
        productService.saveInitialBatch();
    }
}
