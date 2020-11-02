package com.fl.market.domain.repository;

import com.fl.market.domain.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    public List<ProductDTO> getAll();
    public Optional<List<ProductDTO>> getByCategory(long categoryId);
    public Optional<ProductDTO> getProduct(long productId);
    public ProductDTO save(ProductDTO product);
    public void delete(long productId);
    public Optional<ProductDTO> update(long productId, ProductDTO product);

}
