package com.egg.EggNewsintel.web.controller;

import com.egg.EggNewsintel.domain.New;
import com.egg.EggNewsintel.domain.exception.MyException;
import com.egg.EggNewsintel.domain.service.NewService;
import com.egg.EggNewsintel.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class indexController {
    @Autowired
    private NewService newService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String inicio(){

        return "inicio.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_USUARIO', 'ROLE_PERIODISTA')")
    @GetMapping("/index")
    public String index(ModelMap modelo){
        List<New> news = newService.getAll() ;
        modelo.addAttribute("news", news);
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String username, @RequestParam String password,
                           @RequestParam String password2, ModelMap modelo){
        try {
            System.out.println(password +" --------------" + password2);
            userService.save(username,password,password2);
            modelo.put("exito", "El Usuario se ha creado exitosamente");

            return "redirect:/";
        } catch (MyException e) {
            modelo.put("username", username);
            modelo.put("error", e.getMessage());
            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        if(error!=null){
            modelo.put("error", "Usuario o constraseña invalida");
        }
        return "login.html";
    }


}
