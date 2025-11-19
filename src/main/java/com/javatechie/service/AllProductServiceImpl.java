package com.javatechie.service;

import com.javatechie.exception.ProductException;
import com.javatechie.model.Category;
import com.javatechie.model.CreateProductRequest;
import com.javatechie.model.Product;
import com.javatechie.repository.CategoryRepository;
import com.javatechie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AllProductServiceImpl implements AllProductService{

    public AllProductServiceImpl() {
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    UserService userService;

    @Override
    public Product createProduct(CreateProductRequest request) {

        Category topLevel = categoryRepository.findByName(request.getTopCategory());

        if(topLevel == null){
            Category topLevelCategory = new Category();
            topLevelCategory.setName(request.getTopCategory());
            topLevelCategory.setLevel(1);

            topLevel = categoryRepository.save(topLevelCategory);
        }

        Category secondLevel = categoryRepository
                .findByNameAndParent(request.getSecondCategory(),topLevel.getName());

        if(secondLevel == null){
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(request.getSecondCategory());
            secondLevelCategory.setCategoryPrent(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel = categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(request.getThirdCategory(),secondLevel.getName());

        if(thirdLevel == null){
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(request.getThirdCategory());
            thirdLevelCategory.setCategoryPrent(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevel = categoryRepository.save(thirdLevel);
        }

        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setColor(request.getColor());
        product.setDescription(request.getDescription());
        product.setDiscountedPresent(request.getDiscountPercent());
        product.setImageUrl(request.getImageUrl());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setSizes(request.getSizes());
        product.setQuantity(request.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Product updateProduct(Long productId, Product productReq) throws ProductException {
        Product product = findProductById(productId);
        if(productReq.getQuantity()!=0){
            product.setQuantity(productReq.getQuantity());
        }

        return productRepository.save(product);

    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Deleted Product";
    }

    @Override
    public Product findProductById(Long id) throws ProductException {

        Optional<Product> product= productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        throw  new ProductException("Product not found with id "+id);
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes
            , Integer minPrice, Integer maxPrice, Integer minDis, String sort , String stock
            , Integer pageNum, Integer pageSize) throws ProductException {

        Pageable pageable = PageRequest.of(pageNum,pageSize);
        List<Product> productList = productRepository.filterProduct(category,minPrice,maxPrice,minDis,sort);

        if(!colors.isEmpty()){
            productList = productList.stream()
                    .filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor())))
                    .toList();
        }
        if(stock!=null){
            if(stock.equals("in_stock")){
                productList = productList.stream().filter(p->p.getQuantity()>0).toList();
            } else if (stock.equals("out_of_stock")) {
                productList = productList.stream().filter(p->p.getQuantity()<1).toList();
            }
        }

        int startIndex = (int)pageable.getOffset();
        int endIndex = Math.min(startIndex+pageable.getPageSize(),productList.size());


        List<Product> pageContent = productList.subList(startIndex,endIndex);

        Page<Product> filterProducts = new PageImpl<>(pageContent,pageable,productList.size());

        return filterProducts;

    }

    @Override
    public List<Product> findProductsByCategory(String Category) throws ProductException {
        return List.of();
    }
}
