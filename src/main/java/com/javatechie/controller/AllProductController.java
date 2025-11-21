package com.javatechie.controller;

import com.javatechie.exception.ProductException;
import com.javatechie.model.Product;
import com.javatechie.service.AllProductService;
import com.javatechie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AllProductController {

    @Autowired
    private AllProductService productService;

    @GetMapping("allproducts")
    public ResponseEntity<Page<Product>> indProductByCategoryHandler(

            @RequestParam String category , @RequestParam List<String> color,
            @RequestParam List<String> size , @RequestParam int minPrice,@RequestParam int maxPrice,
            @RequestParam int minDiscount ,@RequestParam String sort,@RequestParam String stock,
            @RequestParam int pageNumber , @RequestParam int pageSize
    ) throws ProductException {
        Page<Product> response = productService.getAllProducts(category,color,size,minPrice,maxPrice,minDiscount,sort,stock,pageNumber,pageSize);
        System.out.println("complete products");

        return  new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }



    @GetMapping("/product/id/{productId}")
    public ResponseEntity<Product> getProductById(

            @PathVariable Long productId
    ) throws ProductException {
       Product response = productService.findProductById(productId);
        System.out.println("complete products");

        return  new ResponseEntity<Product>(response, HttpStatus.ACCEPTED);

    }


//    @GetMapping("/products/search")
//    public ResponseEntity<Product> searchProductHandler(
//
//            @RequestParam String q
//
//    ) throws ProductException {
//        Page<Product> response = productService.searchProduct(q);
//        System.out.println("complete products");
//
//        return  new ResponseEntity<>(response, HttpStatus.ACCEPTED);
//
//    }
}
