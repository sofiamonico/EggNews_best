package com.egg.EggNewsintel.persistence.crud;

import com.egg.EggNewsintel.persistence.entity.Noticia;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticiaCrudRepository extends CrudRepository<Noticia, Integer> {

     List<Noticia> findAllByOrderByIdNoticiaDesc();

}
