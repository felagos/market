package com.fl.market.persistence;

import com.fl.market.domain.dto.PurchaseDTO;
import com.fl.market.domain.repository.PurchaseRepository;
import com.fl.market.persistence.crud.CompraCrudRepository;
import com.fl.market.persistence.entity.Compra;
import com.fl.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRespository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraRespository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<PurchaseDTO> getAll() {
        return mapper.toPurchases((List<Compra>) compraRespository.findAll());
    }

    @Override
    public Optional<List<PurchaseDTO>> getByClient(String clientId) {
        return compraRespository.findByIdCliente(clientId).map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public PurchaseDTO save(PurchaseDTO purchase) {
        var compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraRespository.save(compra));
    }

}
