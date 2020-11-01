package com.fl.market.web.controller;

import com.fl.market.domain.service.ProductService;
import com.fl.market.web.response.ResponseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseController<?>> getByCategory(@PathVariable Integer categoryId) {
        return productService.getByCategory(categoryId).map(category -> this.okResponse("Categoria encontrada", category))
                .orElse(this.notFoundResponse("Categoria no encotrada"));
    }

}
