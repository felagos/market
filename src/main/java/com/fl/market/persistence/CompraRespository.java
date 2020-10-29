package com.fl.market.persistence;

import com.fl.market.domain.Purchase;
import com.fl.market.domain.repository.PurchaseRepository;
import com.fl.market.persistence.crud.CompraCrudRepository;
import com.fl.market.persistence.entity.Compra;
import com.fl.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRespository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraRespository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraRespository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraRespository.findByIdCliente(clientId).map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        var compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraRespository.save(compra));
    }

}
