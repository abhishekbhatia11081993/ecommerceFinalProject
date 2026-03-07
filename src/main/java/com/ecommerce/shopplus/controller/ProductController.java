package com.ecommerce.shopplus.controller;

import com.ecommerce.shopplus.model.Product;
import com.ecommerce.shopplus.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String productSearch(@RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String category,
                                Model model) {
        List<Product> products;

        if (keyword != null && !keyword.trim().isEmpty()) {
            products = productService.searchProducts(keyword);
            model.addAttribute("keyword", keyword);
        } else if (category != null && !category.trim().isEmpty()) {
            products = productService.getProductsByCategory(category);
            model.addAttribute("selectedCategory", category);
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("totalProducts", products.size());
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        productService.getProductById(id).ifPresent(product -> {
            model.addAttribute("product", product);
            List<Product> related = productService.getProductsByCategory(product.getCategory());
            related.removeIf(p -> p.getId().equals(id));
            if (related.size() > 4) {
                related = related.subList(0, 4);
            }
            model.addAttribute("relatedProducts", related);
        });
        return "product-detail";
    }
}
