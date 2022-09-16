package com.egg.EggNewsintel.persistence.mapper;

import com.egg.EggNewsintel.domain.Journalist;
import com.egg.EggNewsintel.persistence.entity.Periodista;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JournalistMapper {

    @Mappings({
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "nombreUsuario", target = "userName"),
            @Mapping(source = "fechaAlta", target = "created_at"),
            @Mapping(source = "dtype", target = "role"),
            @Mapping(source = "salarioMensual", target = "monthly_salary")
    })
    Journalist toJournalist(Periodista periodista);
    List<Journalist> toJournalists(List<Periodista> periodistas);

    @InheritInverseConfiguration
    @Mapping(target = "cantidadNoticias", ignore = true)
    Periodista toPeriodista(Journalist journalist);
}
