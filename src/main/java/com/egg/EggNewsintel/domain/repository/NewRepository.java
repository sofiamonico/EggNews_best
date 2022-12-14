package com.egg.EggNewsintel.domain.repository;

import com.egg.EggNewsintel.domain.Journalist;
import com.egg.EggNewsintel.domain.New;
import com.egg.EggNewsintel.persistence.entity.Periodista;

import java.util.List;
import java.util.Optional;

public interface NewRepository {
    List<New> getAll();
    Optional<New> getNoticia(int newId);

    New save(New neww);
    void delete(int productId);
}
