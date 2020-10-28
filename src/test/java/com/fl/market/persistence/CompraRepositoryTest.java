package com.fl.market.persistence;

import com.fl.market.BaseTest;
import com.fl.market.domain.Purchase;
import com.fl.market.domain.PurchaseItem;
import com.fl.market.persistence.crud.CompraCrudRepository;
import com.fl.market.persistence.entity.Compra;
import com.fl.market.persistence.entity.CompraProducto;
import com.fl.market.persistence.entity.Producto;
import com.fl.market.persistence.mapper.PurchaseMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    private final Purchase PURCHASE = new Purchase();
    private final PurchaseItem ITEM = new PurchaseItem();
    private final String CLIENT_ID = "1";
    private final CompraProducto COMPRA_PRODUCTO = new CompraProducto();

    @BeforeEach
    public void setup() {
        COMPRA.setComentario("comment");
        COMPRA.setEstado("activo");
        COMPRA.setIdCliente("1");
        COMPRA.setIdCompra(1L);
        COMPRA.setFechaPago(LocalDateTime.now());
        COMPRA.setMedioPago("tarjeta");

        COMPRA_PRODUCTO.setCantidad(100);
        COMPRA_PRODUCTO.setEstado(true);
        COMPRA_PRODUCTO.setTotal(100000.0);

        //COMPRA.setProductos(Arrays.asList(COMPRA_PRODUCTO));

        ITEM.setActive(true);
        ITEM.setProductId(1L);
        ITEM.setQuantity(1);
        ITEM.setTotal(1.0);

        PURCHASE.setClientId("1");
        PURCHASE.setComment("comment");
        PURCHASE.setDate(LocalDateTime.now());
        PURCHASE.setPurchaseId(1L);
        //PURCHASE.setItems(Arrays.asList(ITEM));
        PURCHASE.setState("D");
        PURCHASE.setPaymentMethod("card");

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

    @Test
    public void get_by_client_id() {
        Mockito.when(compraCrudRespository.findByIdCliente(CLIENT_ID)).thenReturn(Optional.of(COMPRAS));
        var response = compraRespository.getByClient(CLIENT_ID);

        Assertions.assertEquals(response.get().size(), COMPRAS.size());
    }

    @Test
    public void save() {
        Mockito.when(compraCrudRespository.save(COMPRA)).thenReturn(COMPRA);

        var response = compraRespository.save(PURCHASE);
    }


}
