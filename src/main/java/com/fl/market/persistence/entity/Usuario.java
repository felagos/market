package com.fl.market.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "usuarios")
@Data
public class Usuario {

    @Id
    private String email;

    private String password;

}
