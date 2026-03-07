package com.ecommerce.shopplus.config;

import com.ecommerce.shopplus.model.Product;
import com.ecommerce.shopplus.model.User;
import com.ecommerce.shopplus.repository.ProductRepository;
import com.ecommerce.shopplus.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(ProductRepository productRepository,
                      UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            loadUsers();
        }
        if (productRepository.count() == 0) {
            loadProducts();
        }
    }

    private void loadUsers() {
        User user1 = new User("john@example.com", passwordEncoder.encode("password123"), "John Doe");
        user1.setPhone("+1-555-0101");
        userRepository.save(user1);

        User user2 = new User("jane@example.com", passwordEncoder.encode("password123"), "Jane Smith");
        user2.setPhone("+1-555-0102");
        userRepository.save(user2);

        User admin = new User("admin@shopplus.com", passwordEncoder.encode("admin123"), "Admin User");
        admin.setRole("ROLE_ADMIN");
        admin.setPhone("+1-555-0100");
        userRepository.save(admin);
    }

    private void loadProducts() {
        // --- Clothing ---
        createProduct("Classic Fit Cotton Shirt", "Premium cotton shirt with a classic fit. Perfect for both formal and casual occasions. Features button-down collar and adjustable cuffs.", new BigDecimal("49.99"), new BigDecimal("69.99"), "Clothing", "Ralph Lauren", "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=400", null, true, false, true, 50, 4.5);
        createProduct("Slim Fit Denim Jeans", "Modern slim fit jeans crafted from premium stretch denim. Comfortable all-day wear with a stylish silhouette.", new BigDecimal("79.99"), null, "Clothing", "Levi's", "https://images.unsplash.com/photo-1542272604-787c3835535d?w=400", null, false, true, false, 35, 4.2);
        createProduct("Wool Blend Overcoat", "Elegant wool blend overcoat for the winter season. Double-breasted design with satin lining.", new BigDecimal("199.99"), new BigDecimal("299.99"), "Clothing", "Hugo Boss", "https://images.unsplash.com/photo-1539533018447-63fcce2678e3?w=400", null, true, false, true, 15, 4.8);
        createProduct("Velvet Evening Dress", "Stunning velvet evening dress with a flattering A-line silhouette. Perfect for special occasions and dinner parties.", new BigDecimal("149.99"), new BigDecimal("189.99"), "Clothing", "Zara", "https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=400", null, true, true, true, 20, 4.6);
        createProduct("Casual Linen Blazer", "Lightweight linen blazer ideal for spring and summer. Unstructured design for a relaxed yet polished look.", new BigDecimal("129.99"), null, "Clothing", "H&M", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400", null, false, true, false, 25, 4.0);
        createProduct("Graphic Print T-Shirt", "Soft cotton t-shirt with an artistic graphic print. Crew neck design with reinforced seams for durability.", new BigDecimal("24.99"), new BigDecimal("34.99"), "Clothing", "Nike", "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400", null, false, false, true, 100, 4.1);

        // --- Electronics ---
        createProduct("Wireless Noise Cancelling Headphones", "Premium wireless headphones with active noise cancellation. 30-hour battery life with quick charge support.", new BigDecimal("249.99"), new BigDecimal("349.99"), "Electronics", "Sony", "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400", null, true, false, true, 40, 4.7);
        createProduct("Smart Watch Pro", "Advanced smartwatch with health monitoring, GPS tracking, and 5-day battery life. Water resistant to 50 meters.", new BigDecimal("299.99"), null, "Electronics", "Apple", "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400", null, true, true, false, 30, 4.9);
        createProduct("Portable Bluetooth Speaker", "Compact waterproof speaker with 360-degree sound. 12-hour playback time with built-in microphone for calls.", new BigDecimal("79.99"), new BigDecimal("99.99"), "Electronics", "JBL", "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=400", null, false, false, true, 60, 4.3);
        createProduct("4K Ultra HD Action Camera", "Rugged action camera shooting in 4K at 60fps. Waterproof to 30 feet without housing. Electronic image stabilization.", new BigDecimal("349.99"), new BigDecimal("429.99"), "Electronics", "GoPro", "https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?w=400", null, true, true, true, 18, 4.6);
        createProduct("Wireless Charging Pad", "Fast wireless charging pad compatible with all Qi-enabled devices. Sleek minimalist design with LED indicator.", new BigDecimal("29.99"), null, "Electronics", "Samsung", "https://images.unsplash.com/photo-1586953208448-b95a79798f07?w=400", null, false, false, false, 80, 4.0);

        // --- Accessories ---
        createProduct("Leather Crossbody Bag", "Handcrafted genuine leather crossbody bag with adjustable strap. Multiple compartments for organized storage.", new BigDecimal("89.99"), new BigDecimal("119.99"), "Accessories", "Coach", "https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=400", null, true, false, true, 25, 4.4);
        createProduct("Aviator Sunglasses", "Classic aviator sunglasses with polarized lenses and gold-tone metal frame. 100% UV protection.", new BigDecimal("159.99"), null, "Accessories", "Ray-Ban", "https://images.unsplash.com/photo-1572635196237-14b3f281503f?w=400", null, true, true, false, 45, 4.7);
        createProduct("Minimalist Leather Wallet", "Slim bi-fold wallet crafted from premium Italian leather. RFID blocking technology to protect your cards.", new BigDecimal("59.99"), new BigDecimal("79.99"), "Accessories", "Bellroy", "https://images.unsplash.com/photo-1627123424574-724758594e93?w=400", null, false, false, true, 55, 4.3);
        createProduct("Stainless Steel Watch", "Classic stainless steel watch with sapphire crystal glass. Japanese quartz movement with date display.", new BigDecimal("189.99"), new BigDecimal("249.99"), "Accessories", "Seiko", "https://images.unsplash.com/photo-1524592094714-0f0654e20314?w=400", null, true, true, true, 20, 4.8);

        // --- Footwear ---
        createProduct("Running Shoes Ultra Boost", "High-performance running shoes with responsive cushioning. Breathable knit upper with supportive heel counter.", new BigDecimal("129.99"), new BigDecimal("159.99"), "Footwear", "Adidas", "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400", null, true, true, true, 40, 4.5);
        createProduct("Classic Leather Boots", "Timeless leather boots with Goodyear welt construction. Durable rubber sole for all-weather traction.", new BigDecimal("179.99"), null, "Footwear", "Timberland", "https://images.unsplash.com/photo-1608256246200-53e635b5b65f?w=400", null, true, false, false, 22, 4.6);
        createProduct("Canvas Slip-On Sneakers", "Casual canvas slip-on sneakers with cushioned insole. Vulcanized rubber sole and reinforced toe cap.", new BigDecimal("54.99"), new BigDecimal("64.99"), "Footwear", "Vans", "https://images.unsplash.com/photo-1525966222134-fcfa99b8ae77?w=400", null, false, false, true, 70, 4.2);

        // --- Home & Living ---
        createProduct("Scented Soy Candle Set", "Set of 3 hand-poured soy candles in lavender, vanilla, and cedarwood. 40-hour burn time per candle.", new BigDecimal("34.99"), new BigDecimal("44.99"), "Home & Living", "Yankee", "https://images.unsplash.com/photo-1602028915047-37269d1a73f7?w=400", null, false, true, true, 90, 4.4);
        createProduct("Ceramic Pour-Over Coffee Set", "Artisan ceramic pour-over coffee dripper with matching mug. Produces a clean, flavorful brew every time.", new BigDecimal("44.99"), null, "Home & Living", "Hario", "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=400", null, false, true, false, 35, 4.5);
    }

    private void createProduct(String name, String description, BigDecimal price, BigDecimal oldPrice,
                                String category, String brand, String imageUrl, String imageUrl2,
                                boolean featured, boolean newArrival, boolean onSale,
                                int stock, double rating) {
        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setOldPrice(oldPrice);
        p.setCategory(category);
        p.setBrand(brand);
        p.setImageUrl(imageUrl);
        p.setImageUrl2(imageUrl2);
        p.setFeatured(featured);
        p.setNewArrival(newArrival);
        p.setOnSale(onSale);
        p.setStockQuantity(stock);
        p.setRating(rating);
        productRepository.save(p);
    }
}
