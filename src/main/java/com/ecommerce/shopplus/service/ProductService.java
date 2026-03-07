package com.ecommerce.shopplus.service;

import com.ecommerce.shopplus.model.Product;
import com.ecommerce.shopplus.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return productRepository.findAll();
        }
        return productRepository.searchProducts(keyword.trim());
    }

    public List<Product> getFeaturedProducts() {
        return productRepository.findByFeaturedTrue();
    }

    public List<Product> getNewArrivals() {
        return productRepository.findByNewArrivalTrue();
    }

    public List<Product> getSaleProducts() {
        return productRepository.findByOnSaleTrue();
    }

    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }
}
