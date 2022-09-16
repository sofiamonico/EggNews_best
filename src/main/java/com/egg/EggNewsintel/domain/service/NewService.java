package com.egg.EggNewsintel.domain.service;

import com.egg.EggNewsintel.domain.Journalist;
import com.egg.EggNewsintel.domain.New;
import com.egg.EggNewsintel.domain.exception.MyException;
import com.egg.EggNewsintel.domain.repository.JournalistRepository;
import com.egg.EggNewsintel.domain.repository.NewRepository;
import net.iharder.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewService {

    @Autowired
    private NewRepository newRepository;
    @Autowired
    private JournalistRepository journalistRepository;

    public List<New> getAll(){
        return newRepository.getAll();
    };
    public Optional<New> getNoticia(int newId){
        return newRepository.getNoticia(newId);
    };

    @Transactional
    public New save(String title, String body, MultipartFile img, Integer id) throws MyException {
        validar(title, body, img);
        New aNew= new New();
        try {
            aNew.setImage(Base64.encodeBytes(img.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        aNew.setTitle(title);
        aNew.setBody(body);
        aNew.setDate(new Date());
        Optional<Journalist> respuesta = journalistRepository.getJournalist(id);
        if(respuesta.isPresent()){
            Journalist journalist= respuesta.get();
            aNew.setJournalist(journalist);
        }

        return newRepository.save(aNew);
    };

    public void modificarNoticia(Integer id, String titulo, String cuerpo, MultipartFile img) throws MyException {
        Optional<New> respuesta = getNoticia(id);

        if (respuesta.isPresent()) {
            New aNew = respuesta.get();
            if(img != null && !img.isEmpty()){
                try {
                    aNew.setImage(Base64.encodeBytes(img.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(titulo!=null){
                aNew.setTitle(titulo);
            }
            if(cuerpo!=null){
                aNew.setBody(cuerpo);
            }
            newRepository.save(aNew);
        }
    }
    public boolean delete(int newId){
        return getNoticia(newId).map(aNew -> {
            newRepository.delete(newId);
            return true;
        }).orElse(false);
    };

    private void validar(String titulo, String cuerpo, MultipartFile img) throws MyException {
        if (titulo == null || titulo.isEmpty()) {
            throw new MyException("El titulo no puede ser nulo");
        }
        if (cuerpo == null || cuerpo.isEmpty() ) {
            throw new MyException("El cuerpo de la noticia no puede ser nulo");
        }
        if (img == null || img.isEmpty()) {
            throw new MyException("Debe cargar una imagen");
        }

    }
}
