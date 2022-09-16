package com.egg.EggNewsintel.persistence;

import com.egg.EggNewsintel.domain.UserRequest;
import com.egg.EggNewsintel.domain.UserResponse;
import com.egg.EggNewsintel.domain.repository.UserRepository;
import com.egg.EggNewsintel.persistence.crud.UsuarioCrudRepository;
import com.egg.EggNewsintel.persistence.entity.Periodista;
import com.egg.EggNewsintel.persistence.entity.Usuario;
import com.egg.EggNewsintel.persistence.mapper.UserRequestMapper;
import com.egg.EggNewsintel.persistence.mapper.UserResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsarioRepository implements UserRepository {
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;
    @Autowired
    private UserRequestMapper userRequestMapper;
    @Autowired
    private UserResponseMapper userResponseMapper;


    @Override
    public List<UserResponse> getAll() {
        List<Usuario> usuarios= (List<Usuario>) usuarioCrudRepository.findAll();
        return userResponseMapper.toUsers(usuarios);
    }

    @Override
    public Optional<UserResponse> getUserResponse(int userId) {
        return usuarioCrudRepository.findById(userId).map(usuario -> userResponseMapper.toUser(usuario));
    }

    @Override
    public Optional<UserRequest> getUserRequest(int userId) {
        return usuarioCrudRepository.findById(userId).map(usuario -> userRequestMapper.toUser(usuario));
    }

    @Override
    public Optional<UserResponse> findUserByUserName(String userName) {
        return usuarioCrudRepository.findUsuarioByNombreUsuario(userName).map(usuario -> userResponseMapper.toUser(usuario));
    }

    @Override
    public void changeRolToJournalist(double salario, int id, String role) {
        usuarioCrudRepository.changeRolToJournalist(salario,id,role);
    }

    @Override
    public Optional<Usuario> findUsuarioByUserName(String userName) {
        return usuarioCrudRepository.findUsuarioByNombreUsuario(userName);
    }

    @Override
    public UserRequest save(UserRequest userRequest) {
        Usuario usuario = userRequestMapper.toUsuario(userRequest);
        return userRequestMapper.toUser(usuarioCrudRepository.save(usuario));
    }

    @Override
    public void delete(int userId) {
        usuarioCrudRepository.deleteById(userId);
    }
}
