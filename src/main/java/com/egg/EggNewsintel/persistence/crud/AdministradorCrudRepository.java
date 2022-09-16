package com.egg.EggNewsintel.persistence.crud;

import com.egg.EggNewsintel.persistence.entity.Administrador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface AdministradorCrudRepository extends CrudRepository<Administrador,Integer> {
    /*
    @Query("SELECT a.dtype FROM Autor a WHERE id = :id ")
    public String buscarDTypePorId(@RequestParam("id") int id);
     */
}
