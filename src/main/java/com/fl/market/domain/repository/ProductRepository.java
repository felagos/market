package com.fl.market.domain.repository;

import com.fl.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    public List<Product> getAll();
    public Optional<List<Product>> getByCategory(long categoryId);
    public Optional<Product> getProduct(long productId);
    public Product save(Product product);
    public void delete(long productId);
    public Optional<Product> update(long productId, Product product);

}
