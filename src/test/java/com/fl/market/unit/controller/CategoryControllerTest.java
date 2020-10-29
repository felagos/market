package com.fl.market.unit.controller;

import com.fl.market.BaseTest;
import com.fl.market.domain.Product;
import com.fl.market.domain.service.ProductService;
import com.fl.market.web.controller.CategoryController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

public class CategoryControllerTest extends BaseTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private CategoryController controller;

    private final List<Product> PRODUCTS = Arrays.asList(new Product());
    private final Integer CATEGORY_ID = 1;

    @Test
    public void get_products_by_category_id_case_200() {
        Mockito.when(service.getByCategory(anyInt())).thenReturn(Optional.of(PRODUCTS));
        var response = controller.getByCategory(CATEGORY_ID);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getData(), PRODUCTS);
    }

    @Test
    public void get_products_by_category_id_case_404() {
        Mockito.when(service.getByCategory(anyInt())).thenReturn(Optional.empty());
        var response = controller.getByCategory(CATEGORY_ID);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
