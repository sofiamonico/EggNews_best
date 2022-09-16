package com.egg.EggNewsintel.persistence.crud;

import com.egg.EggNewsintel.persistence.entity.Periodista;
import com.egg.EggNewsintel.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PeriodistaCrudRepository extends CrudRepository<Periodista, Integer> {

}
