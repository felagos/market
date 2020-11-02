package com.fl.market.domain.repository;

import com.fl.market.domain.dto.UserDTO;

import java.util.Optional;

public interface UserRepository {

    public Optional<UserDTO> findByEmail(String email);

}
