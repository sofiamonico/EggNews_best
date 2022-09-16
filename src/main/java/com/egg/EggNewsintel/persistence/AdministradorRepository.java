package com.egg.EggNewsintel.persistence;

import com.egg.EggNewsintel.domain.Admin;
import com.egg.EggNewsintel.domain.repository.AdminRepository;
import com.egg.EggNewsintel.persistence.crud.AdministradorCrudRepository;
import com.egg.EggNewsintel.persistence.entity.Administrador;
import com.egg.EggNewsintel.persistence.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdministradorRepository implements AdminRepository {
    @Autowired
    private AdministradorCrudRepository adminCrud;
    @Autowired
    private AdminMapper mapper;

    @Override
    public List<Admin> getAll() {
        List<Administrador> administradors = (List<Administrador>) adminCrud.findAll();
        return mapper.toAdmins(administradors) ;
    }

    @Override
    public Optional<Admin> getAdmin(int adminId) {
        return adminCrud.findById(adminId).map(administrador -> mapper.toAdmin(administrador)) ;
    }



    @Override
    public Admin save(Admin admin) {
        Administrador administrador = mapper.toAdministrador(admin);
        return mapper.toAdmin(adminCrud.save(administrador));
    }

    @Override
    public void delete(int adminId) {
        adminCrud.deleteById(adminId);
    }
}
