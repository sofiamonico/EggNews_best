package com.egg.EggNewsintel.persistence;

import com.egg.EggNewsintel.domain.New;
import com.egg.EggNewsintel.domain.repository.NewRepository;
import com.egg.EggNewsintel.persistence.crud.NoticiaCrudRepository;
import com.egg.EggNewsintel.persistence.entity.Noticia;
import com.egg.EggNewsintel.persistence.mapper.NewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NoticiaRepository implements NewRepository {

    @Autowired
    private NoticiaCrudRepository noticiaCrudRepository;
    @Autowired
    private NewMapper newMapper;

    @Override
    public List<New> getAll(){
        List<Noticia> noticias = noticiaCrudRepository.findAllByOrderByIdNoticiaDesc();
        return newMapper.toNews(noticias);
    }

    @Override
    public Optional<New> getNoticia(int newId){
        return noticiaCrudRepository.findById(newId).map(noticia -> newMapper.toNew(noticia));
    }
    @Override
    public New save(New neww){
        Noticia noticia = newMapper.toNoticia(neww);
        return newMapper.toNew(noticiaCrudRepository.save(noticia));
    }
    @Override
    public void delete(int newId){
        noticiaCrudRepository.deleteById(newId);
    }
}
