package com.egg.EggNewsintel.domain.service;

import com.egg.EggNewsintel.domain.UserRequest;
import com.egg.EggNewsintel.domain.UserResponse;
import com.egg.EggNewsintel.domain.exception.MyException;
import com.egg.EggNewsintel.domain.repository.UserRepository;
import com.egg.EggNewsintel.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;


@Service
public class UserService implements UserDetailsService {

    /*
    private static class SecurityUser extends org.springframework.security.core.userdetails.User {
        public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }
    }

     */

    ;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserRequest save(String username, String password, String password2) throws MyException {
        validar(username, password, password2);
        UserRequest userRequest = new UserRequest();

        userRequest.setUserName(username);
        userRequest.setPassword(new BCryptPasswordEncoder().encode(password));
        userRequest.setCreated_at(new Date());
        return userRepository.save(userRequest);
    }

    public List<UserResponse> getAll(){
        return userRepository.getAll();
    }

    public UserResponse getOneById(int id){
        Optional<UserResponse> respuesta = userRepository.getUserResponse(id);
        if(respuesta.isPresent()){
            UserResponse userResponse = respuesta.get();
            return userResponse;
        }
        return null;
    }
    @Transactional
    public void changeRolToJournalist(int id, String role, double salary) throws MyException {
        validarConvertirPeriodista(role,salary);
        userRepository.changeRolToJournalist(salary,id, role);
    }


    private void validar(String username, String password, String password2) throws MyException {
        if (username == null || username.isEmpty()) {
            throw new MyException("El nombre de usuario no puede ser nulo");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new MyException("La constraseña no puede ser nula");
        }
        if (!password.equals(password2)) {
            throw new MyException("Las contraseñas deben coincidir");
        }
    }

    private void validarConvertirPeriodista(String role, double salary ) throws MyException {
        if (role == null || role.isEmpty()) {
            throw new MyException("El rol no puede ser nulo");
        }
        if (salary == 0.0) {
            throw new MyException("Debe ingresar un salario");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> respuesta = userRepository.findUsuarioByUserName(username);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority(usuario.getRol());
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            User user = new User(usuario.getNombreUsuario(), usuario.getContraseña(), permisos);
            return user;
        } else {
            return null;
        }

    }
}

