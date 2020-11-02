package com.fl.market.persistence;

import com.fl.market.domain.dto.UserDTO;
import com.fl.market.domain.repository.UserRepository;
import com.fl.market.persistence.crud.UsuarioCrudRepository;
import com.fl.market.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return usuarioCrudRepository.findByEmail(email).map(u -> mapper.toUser(u));
    }

}
