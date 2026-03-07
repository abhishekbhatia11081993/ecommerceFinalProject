package com.ecommerce.shopplus.controller;

import com.ecommerce.shopplus.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid email or password!");
        }
        if (logout != null) {
            model.addAttribute("successMessage", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String fullName,
                               RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(email, password, fullName);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/account")
    public String accountPage(Model model, java.security.Principal principal) {
        if (principal != null) {
            userService.findByEmail(principal.getName())
                    .ifPresent(user -> model.addAttribute("user", user));
        }
        return "account";
    }
}
