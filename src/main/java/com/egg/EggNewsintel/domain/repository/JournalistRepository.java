package com.egg.EggNewsintel.domain.repository;

import com.egg.EggNewsintel.domain.Journalist;
import com.egg.EggNewsintel.domain.New;

import java.util.List;
import java.util.Optional;

public interface JournalistRepository {
    List<Journalist> getAll();
    Optional<Journalist> getJournalist(int newId);
    Optional<Journalist> findPeriodistaByNombreUsuario(String nombreUsuario);
    Journalist save(Journalist journalist);
    void delete(int journalistId);
}
