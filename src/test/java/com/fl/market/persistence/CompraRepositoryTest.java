package com.fl.market.persistence;

import com.fl.market.BaseTest;
import com.fl.market.domain.Purchase;
import com.fl.market.persistence.crud.CompraCrudRepository;
import com.fl.market.persistence.entity.Compra;
import com.fl.market.persistence.mapper.PurchaseMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class CompraRepositoryTest extends BaseTest {

    @Mock
    private CompraCrudRepository compraCrudRespository;

    @Spy
    private PurchaseMapper mapper = Mappers.getMapper(PurchaseMapper.class);

    @InjectMocks
    private CompraRespository compraRespository;

    private final List<Compra> COMPRAS = new ArrayList<>();
    private final Compra COMPRA = new Compra();

    @BeforeEach
    public void setup() {
        COMPRA.setComentario("comment");
        COMPRA.setEstado("activo");
        COMPRA.setIdCliente("1");
        COMPRA.setIdCompra(1L);
        COMPRA.setFechaPago(LocalDateTime.now());
        COMPRA.setMedioPago("tarjeta");

        COMPRAS.add(COMPRA);
    }

    @Test
    public void get_all() {
        Mockito.when(compraCrudRespository.findAll()).thenReturn(COMPRAS);
        var response = compraRespository.getAll();
        var purchase = response.get(0);

        Assertions.assertEquals(response.size(), COMPRAS.size());
        Assertions.assertEquals(purchase.getClientId(), COMPRA.getIdCliente());
        Assertions.assertEquals(purchase.getComment(), COMPRA.getComentario());
    }


}
