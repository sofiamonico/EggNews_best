package com.egg.EggNewsintel.persistence.mapper;

import com.egg.EggNewsintel.domain.UserRequest;
import com.egg.EggNewsintel.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

    @Mappings({
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "nombreUsuario", target = "userName"),
            @Mapping(source = "contraseña", target = "password"),
            @Mapping(source = "fechaAlta", target = "created_at"),
    })
    UserRequest toUser(Usuario usuario);
    List<UserRequest> toUsers(List<Usuario> usuarios);

    @InheritInverseConfiguration
    Usuario toUsuario(UserRequest userRequest);
}
