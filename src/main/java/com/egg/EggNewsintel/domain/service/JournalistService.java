package com.egg.EggNewsintel.domain.service;

import com.egg.EggNewsintel.domain.Journalist;
import com.egg.EggNewsintel.domain.repository.JournalistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class JournalistService {

    @Autowired
    public JournalistRepository journalistRepository;

    @Transactional
    public Optional<Journalist> findPeriodistaByNombreUsuario(String nombreUsuario) {
        return journalistRepository.findPeriodistaByNombreUsuario(nombreUsuario);
    }
}
