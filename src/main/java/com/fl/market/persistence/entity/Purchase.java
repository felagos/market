package com.fl.market.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "compras")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idPurchase;

    @Column(name = "id_cliente")
    private Long idClient;

    @Column(name = "fecha_pago")
    private LocalDateTime payDate;

    @Column(name = "medio_pago")
    private String paymentMethod;

    @Column(name = "comentario")
    private String comment;

    @Column(name = "estado")
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "product")
    private List<ProductPurchase> productPurchases;

}
