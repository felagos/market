package com.fl.market.web.controller;

import com.fl.market.web.response.ResponseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController extends BaseController {

    @GetMapping("/")
    public ResponseEntity<ResponseController<?>> ping() {
        return this.okResponse("Server up !!", null);
    }

}
