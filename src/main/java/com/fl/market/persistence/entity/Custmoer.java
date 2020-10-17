package com.fl.market.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "clientes")
public class Custmoer {

    @Id
    private String id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastName;

    @Column(name = "celular")
    private Integer phoneNumber;

    @Column(name = "direccion")
    private String address;

    @Column(name = "correo_electronico")
    private String email;

}
