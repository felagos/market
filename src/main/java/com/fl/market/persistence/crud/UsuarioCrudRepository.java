package com.fl.market.persistence.crud;

import com.fl.market.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, String> {

    public Optional<Usuario> findByEmail(String email);

}
