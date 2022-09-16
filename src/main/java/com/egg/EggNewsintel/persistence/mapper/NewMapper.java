package com.egg.EggNewsintel.persistence.mapper;

import com.egg.EggNewsintel.domain.New;
import com.egg.EggNewsintel.persistence.entity.Noticia;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {JournalistMapper.class})
public interface NewMapper {

    @Mappings({
            @Mapping(source = "idNoticia", target = "newId"),
            @Mapping(source = "titulo", target = "title"),
            @Mapping(source = "cuerpo", target = "body"),
            @Mapping(source = "img", target = "image"),
            @Mapping(source = "fecha_publicacion", target = "date"),
            @Mapping(source = "periodista", target = "journalist"),
    })
    New toNew(Noticia noticia);
    List<New> toNews(List<Noticia> noticia);

    @InheritInverseConfiguration
    Noticia toNoticia(New neww);
}
