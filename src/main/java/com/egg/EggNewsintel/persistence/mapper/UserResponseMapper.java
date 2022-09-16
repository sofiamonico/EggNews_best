package com.egg.EggNewsintel.persistence.mapper;
import com.egg.EggNewsintel.domain.UserResponse;
import com.egg.EggNewsintel.persistence.entity.Periodista;
import com.egg.EggNewsintel.persistence.entity.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper{

    @Mappings({
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "nombreUsuario", target = "userName"),
            @Mapping(source = "fechaAlta", target = "created_at"),
            @Mapping(source = "dtype", target = "role"),
            @Mapping(source = "usuario", target = "monthly_salary", qualifiedByName = "getSalary")
    })
    UserResponse toUser(Usuario usuario);
    List<UserResponse> toUsers(List<Usuario> usuarios);

   // expression = "java(usuario instanceof Periodista ? usuario.salario_mensual(): null"
    @Named("getSalary")
    default Double getSalary(Usuario usuario){
        return usuario instanceof Periodista ? ((Periodista) usuario).getSalarioMensual() : null;
    }

    @InheritInverseConfiguration
    @Mapping(target = "contraseña", ignore = true)
    Usuario toUsuario(UserResponse userResponse);
}
