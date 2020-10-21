package com.fl.market.web.controller;

import com.fl.market.domain.Product;
import com.fl.market.domain.service.ProductService;
import com.fl.market.web.response.ResponseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity getAll() {
        return this.okResponse("Productos encontrados", productService.getAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProduct(@PathVariable Integer productId) {
        return productService.getProduct(productId).map(product -> this.okResponse("Producto encontrado", product))
                .orElse(this.notFoundResponse("Producto no encontrado"));
    }

    @PutMapping("/{productId}")
    public ResponseEntity update(@PathVariable Long productId, @RequestBody Product product) {
        return productService.update(productId, product).map(newProduct -> this.okResponse("Producto actualizado", newProduct))
                .orElse(this.notFoundResponse("Producto no encontrado"));
    }

    @PostMapping("/")
    public ResponseEntity save(Product product) {
        return this.createdResponse("Producto creado", productService.save(product));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity delete(@PathVariable Integer productId) {
        var response = productService.delete(productId);
        if (response) return this.okResponse("Producto borrado", null);
        return this.notFoundResponse("Producto no encontrado");
    }

}
