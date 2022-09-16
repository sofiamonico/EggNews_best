package com.egg.EggNewsintel.persistence.mapper;

import com.egg.EggNewsintel.domain.Admin;

import com.egg.EggNewsintel.persistence.entity.Administrador;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    @Mappings({
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "nombreUsuario", target = "userName"),
            @Mapping(source = "contraseña", target = "password"),
            @Mapping(source = "fechaAlta", target = "created_at"),
    })
    Admin toAdmin(Administrador administrador);
    List<Admin> toAdmins(List<Administrador> administrador);

    @InheritInverseConfiguration
    Administrador toAdministrador(Admin admin);

}
