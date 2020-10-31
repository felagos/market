package com.fl.market.unit.services;

import com.fl.market.unit.controller.BaseUnitTest;
import com.fl.market.domain.Product;
import com.fl.market.domain.repository.ProductRepository;
import com.fl.market.domain.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class ProductServiceTest extends BaseUnitTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private final Product PRODUCT = new Product();
    private final List<Product> PRODUCTS = Arrays.asList(new Product());
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
    public void get_all() {
        Mockito.when(productRepository.getAll()).thenReturn(PRODUCTS);
        var response = productService.getAll();

        Assertions.assertEquals(response.size(), PRODUCTS.size());
        Assertions.assertEquals(response, PRODUCTS);
    }

    @Test
    public void get_product() {
        Mockito.when(productRepository.getProduct(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        var response = productService.getProduct(PRODUCT_ID);

        Assertions.assertEquals(response.get(), PRODUCT);
    }

    @Test
    public void get_product_not_founded() {
        Mockito.when(productRepository.getProduct(PRODUCT_ID)).thenReturn(Optional.empty());
        var response = productService.getProduct(PRODUCT_ID);

        Assertions.assertEquals(response.isEmpty(), true);
    }

    @Test
    public void get_product_by_category_id() {
        Mockito.when(productRepository.getByCategory(PRODUCT_ID)).thenReturn(Optional.of(PRODUCTS));
        var response = productService.getByCategory(PRODUCT_ID);

        Assertions.assertEquals(response.get(), PRODUCTS);
    }

    @Test
    public void get_product_by_category_id_not_founded() {
        Mockito.when(productRepository.getByCategory(PRODUCT_ID)).thenReturn(Optional.empty());
        var response = productService.getByCategory(PRODUCT_ID);

        Assertions.assertEquals(response.isEmpty(), true);
    }

    @Test
    public void delete_product() {
        Mockito.when(productRepository.getProduct(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        Mockito.doNothing().when(productRepository).delete(PRODUCT_ID);
        var response = productService.delete(PRODUCT_ID);

        Assertions.assertEquals(response, true);
    }

    @Test
    public void delete_product_not_founded() {
        Mockito.when(productRepository.getProduct(PRODUCT_ID)).thenReturn(Optional.empty());
        Mockito.doNothing().when(productRepository).delete(PRODUCT_ID);
        var response = productService.delete(PRODUCT_ID);

        Assertions.assertEquals(response, false);
    }

    @Test
    public void save_product() {
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(PRODUCT);
        var response = productService.save(PRODUCT);

        Assertions.assertEquals(response, PRODUCT);
    }

    @Test
    public void update_product() {
        Mockito.when(productRepository.update(Long.valueOf(PRODUCT_ID), PRODUCT)).thenReturn(Optional.of(PRODUCT));
        var response = productService.update(Long.valueOf(PRODUCT_ID), PRODUCT);

        Assertions.assertEquals(response.get(), PRODUCT);
    }

    @Test
    public void update_product_not_founded() {
        Mockito.when(productRepository.update(Long.valueOf(PRODUCT_ID), PRODUCT)).thenReturn(Optional.empty());
        var response = productService.update(Long.valueOf(PRODUCT_ID), PRODUCT);

        Assertions.assertEquals(response.isEmpty(), true);
    }

}
