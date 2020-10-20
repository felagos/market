package com.fl.market.domain.repository;

import com.fl.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    public List<Product> getAll();
    public Optional<List<Product>> getByCategory(long categoryId);
    public Optional<List<Product>> getScarseProducts(int quantity);
    public Optional<Product> getProduct(long productId);
    public Product save(Product product);
    public void delete(long productId);

}
