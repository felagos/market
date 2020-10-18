package com.fl.market.persistence.crud;

import com.fl.market.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCrudRepository extends CrudRepository<Product, Long> {

    public List<Product> findByIdCategoryOrderByNameAsc(Long idCategory);

    public Optional<List<Product>> findByStockLessThanAndState(Integer stock, Boolean state);

}
