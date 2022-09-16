package com.egg.EggNewsintel.persistence.mapper;

import com.egg.EggNewsintel.domain.Journalist;
import com.egg.EggNewsintel.domain.New;
import com.egg.EggNewsintel.persistence.entity.Noticia;
import com.egg.EggNewsintel.persistence.entity.Periodista;
import com.egg.EggNewsintel.persistence.entity.Usuario;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface JournalistMapper {

    @Mappings({
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "nombreUsuario", target = "userName"),
            @Mapping(source = "fechaAlta", target = "created_at"),
            @Mapping(source = "dtype", target = "role"),
            @Mapping(source = "salarioMensual", target = "monthly_salary"),
            @Mapping(source = "periodista", target = "news", qualifiedByName = "changeNews")
    })
    Journalist toJournalist(Periodista periodista);
    List<Journalist> toJournalists(List<Periodista> periodistas);

    @Named("changeNews")
    default List<New> getSalary(Periodista periodista){
        List<Noticia> noticias = periodista.getNoticias();
        List<New> news = new ArrayList<>();
        noticias.forEach(noticia -> {
            New aNew = new New();
            aNew.setNewId(noticia.getIdNoticia());
            aNew.setTitle(noticia.getTitulo());
            aNew.setDate(noticia.getFecha_publicacion());
            aNew.setBody(noticia.getCuerpo());
            aNew.setImage(noticia.getImg());
            news.add(aNew);
        });
        return news;
    }

    @InheritInverseConfiguration
    @Mapping(target = "noticias", ignore = true)
    Periodista toPeriodista(Journalist journalist);
}
