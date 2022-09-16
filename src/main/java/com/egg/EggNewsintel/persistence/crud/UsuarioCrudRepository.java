package com.egg.EggNewsintel.persistence.crud;

import com.egg.EggNewsintel.domain.UserResponse;
import com.egg.EggNewsintel.persistence.entity.Noticia;
import com.egg.EggNewsintel.persistence.entity.Periodista;
import com.egg.EggNewsintel.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer> {

    @Modifying
    @Query("UPDATE Usuario u SET u.dtype= :role, u.salarioMensual= :salario WHERE u.idUsuario= :id")
    void changeRolToJournalist(@Param("salario") double salario,
                                  @Param("id") int id, @Param("role") String role);
    Optional<Usuario> findUsuarioByNombreUsuario(String nombreUsuario);


}
