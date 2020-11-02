package com.fl.market.unit.controller;

import com.fl.market.domain.dto.ProductDTO;
import com.fl.market.domain.service.ProductService;
import com.fl.market.unit.BaseUnitTest;
import com.fl.market.web.controller.ProductController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

public class ProductControllerTest extends BaseUnitTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private final List<ProductDTO> PRODUCTS = Arrays.asList(new ProductDTO());
    private final ProductDTO PRODUCT = new ProductDTO();
    private final Integer PRODUCT_ID = 1;

    @BeforeEach
    public void setup() {
        PRODUCT.setName("Laptop");
        PRODUCT.setCategoryId(1);
        PRODUCT.setPrice(20000);
        PRODUCT.setStock(20);
        PRODUCT.setActive(true);
    }

    @Test
    public void get_all_products() {
        Mockito.when(productService.getAll()).thenReturn(PRODUCTS);
        var response = productController.getAll();

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getData(), PRODUCTS );
    }

    @Test
    public void get_product_case_200() {
        Mockito.when(productService.getProduct(anyInt())).thenReturn(Optional.of(PRODUCT));
        var response = productController.getProduct(PRODUCT_ID);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getData(), PRODUCT );
    }

    @Test
    public void get_product_case_404() {
        Mockito.when(productService.getProduct(anyInt())).thenReturn(Optional.empty());
        var response = productController.getProduct(PRODUCT_ID);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND );
    }

    @Test
    public void save_product() {
        Mockito.when(productService.save(any(ProductDTO.class))).thenReturn(PRODUCT);
        var response = productController.save(PRODUCT);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getData(), PRODUCT );
    }

    @Test
    public void delete_product() {
        Mockito.when(productService.delete(anyInt())).thenReturn(true);
        var response = productController.delete(PRODUCT_ID);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getData(), null);
    }

    @Test
    public void delete_product_not_found() {
        Mockito.when(productService.delete(anyInt())).thenReturn(false);
        var response = productController.delete(PRODUCT_ID);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void update_product_not_found() {
        Mockito.when(productService.update(anyLong(), any(ProductDTO.class))).thenReturn(Optional.empty());
        var response = productController.update(Long.valueOf(PRODUCT_ID), PRODUCT);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void update_product() {
        Mockito.when(productService.update(anyLong(), any(ProductDTO.class))).thenReturn(Optional.of(PRODUCT));
        var response = productController.update(Long.valueOf(PRODUCT_ID), PRODUCT);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getData(), PRODUCT);
    }



}
