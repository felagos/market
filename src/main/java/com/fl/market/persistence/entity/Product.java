package com.fl.market.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "productos")
public class Product {

    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(name = "nombre")
    private String name;

    @Column(name = "id_categoria")
    private Long idCategory;

    @Column(name = "codigo_barra")
    private String barCode;

    @Column(name = "precio_venta")
    private Integer price;

    @Column(name = "cantidad_stock")
    private Integer stock;

    @Column(name = "estado")
    private Boolean state;

}
