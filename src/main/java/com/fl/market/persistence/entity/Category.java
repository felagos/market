package com.fl.market.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categorias")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategory;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "estado")
    private Boolean state;

}
