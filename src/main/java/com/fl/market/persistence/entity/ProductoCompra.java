package com.fl.market.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "compras_productos")
public class ProductoCompra {

    @EmbeddedId
    private ProductoCompraPK id;

    @Column(name = "cantidad")
    private Integer cantidad;

    private Double total;

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

}
