package com.javatechie.service;

import com.javatechie.exception.ProductException;
import com.javatechie.model.CreateProductRequest;
import com.javatechie.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AllProductService {

    public Product createProduct(CreateProductRequest createProductRequest);

    public Product updateProduct(Long productId,Product product) throws ProductException;

    public String deleteProduct(Long productId) throws ProductException;

    public Product findProductById(Long id) throws ProductException;

    public Page<Product> getAllProducts
            (String category,List<String> colors,List<String> sizes,
             Integer minPrice, Integer maxPrice, Integer minDis, String sort, String stock,
             Integer pageNum, Integer pageSize) throws ProductException;

    public List<Product> findProductsByCategory(String Category) throws ProductException;


}
