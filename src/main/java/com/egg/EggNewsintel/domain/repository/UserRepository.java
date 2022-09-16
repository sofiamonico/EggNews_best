package com.egg.EggNewsintel.domain.repository;

import com.egg.EggNewsintel.domain.UserRequest;
import com.egg.EggNewsintel.domain.UserResponse;
import com.egg.EggNewsintel.persistence.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<UserResponse> getAll();
    Optional<UserResponse> getUserResponse(int userId);
    Optional<UserRequest> getUserRequest(int userId);
    Optional<UserResponse> findUserByUserName(String userName);
    void changeRolToJournalist(double salario, int id,String role);
    Optional<Usuario> findUsuarioByUserName(String userName);
    UserRequest save(UserRequest userRequest);
    void delete(int userId);
}
