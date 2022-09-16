package com.egg.EggNewsintel.persistence.crud;

import com.egg.EggNewsintel.domain.Journalist;
import com.egg.EggNewsintel.persistence.entity.Periodista;
import com.egg.EggNewsintel.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PeriodistaCrudRepository extends CrudRepository<Periodista, Integer> {

    Optional<Periodista> findPeriodistaByNombreUsuario(String nombreUsuario);
}
