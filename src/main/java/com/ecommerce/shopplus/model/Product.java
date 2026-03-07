package com.ecommerce.shopplus.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private BigDecimal oldPrice;

    private String category;

    private String brand;

    private String imageUrl;

    private String imageUrl2;

    private boolean featured;

    private boolean newArrival;

    private boolean onSale;

    private int stockQuantity;

    private double rating;

    public Product() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getOldPrice() { return oldPrice; }
    public void setOldPrice(BigDecimal oldPrice) { this.oldPrice = oldPrice; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getImageUrl2() { return imageUrl2; }
    public void setImageUrl2(String imageUrl2) { this.imageUrl2 = imageUrl2; }

    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }

    public boolean isNewArrival() { return newArrival; }
    public void setNewArrival(boolean newArrival) { this.newArrival = newArrival; }

    public boolean isOnSale() { return onSale; }
    public void setOnSale(boolean onSale) { this.onSale = onSale; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}
