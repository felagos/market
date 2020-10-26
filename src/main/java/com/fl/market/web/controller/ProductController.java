package com.fl.market.web.controller;

import com.fl.market.domain.Product;
import com.fl.market.domain.service.ProductService;
import com.fl.market.web.response.ResponseController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation("Get all products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity getAll() {
        return this.okResponse("Productos encontrados", productService.getAll());
    }

    @GetMapping("/{productId}")
    @ApiOperation("Search a product by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity getProduct(@ApiParam(value = "The id product", required = true, example = "7") @PathVariable Integer productId) {
        return productService.getProduct(productId).map(product -> this.okResponse("Producto encontrado", product))
                .orElse(this.notFoundResponse("Producto no encontrado"));
    }

    @PutMapping("/{productId}")
    public ResponseEntity update(@PathVariable Long productId, @RequestBody Product product) {
        return productService.update(productId, product).map(newProduct -> this.okResponse("Producto actualizado", newProduct))
                .orElse(this.notFoundResponse("Producto no encontrado"));
    }

    @PostMapping("/")
    public ResponseEntity save(@RequestBody Product product) {
        return this.createdResponse("Producto creado", productService.save(product));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity delete(@PathVariable Integer productId) {
        var response = productService.delete(productId);
        if (response) return this.okResponse("Producto borrado", null);
        return this.notFoundResponse("Producto no encontrado");
    }

}
