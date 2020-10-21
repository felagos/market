package com.fl.market.persistence.crud;

import com.fl.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Producto, Long> {

    public List<Producto> findByIdCategoriaOrderByNombreAsc(Long idCategoria);
    public Optional<List<Producto>> findByCantidadStockLessThanAndEstado(Integer cantidadStock, Boolean estado);

}
