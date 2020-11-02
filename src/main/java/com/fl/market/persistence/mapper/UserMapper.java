package com.fl.market.persistence.mapper;

import com.fl.market.domain.dto.UserDTO;
import com.fl.market.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
    })
    public UserDTO toUser(Usuario usuario);

    @InheritInverseConfiguration
    public Usuario toUsuario(UserDTO user);

}
