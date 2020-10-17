package com.fl.market.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ProductPurchasePK implements Serializable {

    @Column(name = "id_compra")
    private Long idPurchase;

    @Column(name = "id_producto")
    private Long idProduct;

}
