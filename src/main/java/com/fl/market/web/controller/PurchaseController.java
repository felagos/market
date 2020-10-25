package com.fl.market.web.controller;

import com.fl.market.domain.Purchase;
import com.fl.market.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController extends BaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/")
    public ResponseEntity getAll() {
        return this.okResponse("Compras encontradas", purchaseService.getAll());
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity getByClient(@PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId)
                .map(purchases -> this.okResponse("Compras encontradas", purchases))
                .orElse(this.notFoundResponse("Cliente no encontrado"));
    }

    @PostMapping("/")
    public ResponseEntity save(@RequestBody Purchase purchase) {
        return this.createdResponse("Compra creada", purchaseService.save(purchase));
    }

}
