package com.fl.market.persistence;

import com.fl.market.domain.Product;
import com.fl.market.domain.repository.ProductRepository;
import com.fl.market.persistence.crud.ProductoCrudRepository;
import com.fl.market.persistence.entity.Producto;
import com.fl.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        var products = (List<Producto>) this.productCrudRepository.findAll();
        return this.mapper.toProducts(products);
    }

    @Override
    public Optional<List<Product>> getByCategory(long categoryId) {
        var productos = this.productCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(this.mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        return this.productCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true).map(productos -> this.mapper.toProducts(productos));
    }

    @Override
    public Optional<Product> getProduct(long productId) {
        return this.productCrudRepository.findById(productId).map(producto -> this.mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = this.mapper.toProducto(product);
        var newProduct = this.productCrudRepository.save(producto);
        return this.mapper.toProduct(newProduct);
    }

    @Override
    public void delete(long productId) {
        this.productCrudRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> update(long productId, Product product) {
        return this.getProduct(productId).map(p -> {
            product.setProductId(p.getProductId());
            return this.save(product);
        });
    }
}
