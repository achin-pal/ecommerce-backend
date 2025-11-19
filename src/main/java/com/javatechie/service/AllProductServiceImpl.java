package com.javatechie.service;

import com.javatechie.exception.ProductException;
import com.javatechie.model.Category;
import com.javatechie.model.CreateProductRequest;
import com.javatechie.model.Product;
import com.javatechie.repository.CategoryRepository;
import com.javatechie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AllProductServiceImpl implements AllProductService{

    public AllProductServiceImpl() {
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

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
        product.
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        return "";
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDis, String sort, Integer pageNum, Integer pageSize) throws ProductException {
        return null;
    }

    @Override
    public List<Product> findProductsByCategory(String Category) throws ProductException {
        return List.of();
    }
}
