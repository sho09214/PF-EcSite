package com.example.pf_ecsite.controller;

import com.example.pf_ecsite.model.Product;
import com.example.pf_ecsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = productService.getAllProducts();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    // 商品を新しく作るための画面にアクセスしたときの処理を書きます。
    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "create";
    }

    // Web上で新しい商品情報を送信したとき（保存ボタンを押したとき）の処理を書きます。
    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    // 商品情報を更新するための画面にアクセスしたときの処理を書きます。
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){
        Product product = productService.getProductById(id).orElse(null);
        model.addAttribute("product", product);
        return "edit";
    }

    // 商品情報を削除するときの処理を書きます。
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id) {
        this.productService.deleteProduct(id);
        return "redirect:/";
    }

    // 商品の詳細情報を表示する画面にアクセスしたときの処理を書きます。
    @GetMapping("/productDetail/{id}")
    public String productDetail(@PathVariable(value = "id") long id, Model model) {
        Product product = productService.getProductById(id).orElse(null);
        model.addAttribute("product", product);
        return "detail";
    }
}
