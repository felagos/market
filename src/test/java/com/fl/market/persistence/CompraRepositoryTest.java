package com.fl.market.persistence;

import com.fl.market.BaseTest;
import com.fl.market.domain.Purchase;
import com.fl.market.domain.PurchaseItem;
import com.fl.market.persistence.crud.CompraCrudRepository;
import com.fl.market.persistence.entity.Compra;
import com.fl.market.persistence.entity.CompraProducto;
import com.fl.market.persistence.mapper.PurchaseItemMapper;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class CompraRepositoryTest extends BaseTest {

    @Mock
    private CompraCrudRepository compraCrudRespository;

    @Spy
    private PurchaseMapper mapper = Mappers.getMapper(PurchaseMapper.class);

    @Spy
    private PurchaseItemMapper itemMapper= Mappers.getMapper(PurchaseItemMapper.class);

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
        COMPRA_PRODUCTO.setTotal(1000088.9);

        ITEM.setActive(true);
        ITEM.setProductId(1L);
        ITEM.setQuantity(1);
        ITEM.setTotal(1.0);

        PURCHASE.setClientId("4546221");
        PURCHASE.setComment("comment");
        PURCHASE.setDate(LocalDateTime.now());
        PURCHASE.setState("P");
        PURCHASE.setPaymentMethod("E");
        PURCHASE.setItems(new ArrayList<>());

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

        Mockito.when(compraCrudRespository.save(any(Compra.class))).thenReturn(COMPRA);
        Mockito.when(itemMapper.toPurchaseItem(any(CompraProducto.class))).thenReturn(ITEM);

        var response = compraRespository.save(PURCHASE);

        Assertions.assertEquals(response.getClientId(), COMPRA.getIdCliente());
        Assertions.assertEquals(response.getComment(), COMPRA.getComentario());
        Assertions.assertEquals(response.getDate(), COMPRA.getFechaPago());
        Assertions.assertEquals(response.getPaymentMethod(), COMPRA.getMedioPago());
    }


}
