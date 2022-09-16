package com.egg.EggNewsintel.domain.repository;

import com.egg.EggNewsintel.domain.Admin;
import com.egg.EggNewsintel.domain.Journalist;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    List<Admin> getAll();
    Optional<Admin> getAdmin(int adminId);


    Admin save(Admin admin);
    void delete(int adminId);
}
