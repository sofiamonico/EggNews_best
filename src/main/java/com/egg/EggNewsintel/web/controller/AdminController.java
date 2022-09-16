package com.egg.EggNewsintel.web.controller;


import com.egg.EggNewsintel.domain.Admin;
import com.egg.EggNewsintel.domain.New;
import com.egg.EggNewsintel.domain.UserResponse;
import com.egg.EggNewsintel.domain.exception.MyException;
import com.egg.EggNewsintel.domain.service.AdminService;
import com.egg.EggNewsintel.domain.service.NewService;
import com.egg.EggNewsintel.domain.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private NewService newService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public String indexAdmin(){

        return "admin.html";
    }

    @GetMapping("/noticias")
    public String adminNoticias(ModelMap modelo){
        List<New> news = newService.getAll() ;
        modelo.addAttribute("news", news);
        return "admin_news.html";
    }

    @GetMapping("/usuarios")
    public String adminUsuarios(ModelMap modelo){
        List<UserResponse> users = userService.getAll();
        modelo.addAttribute("users", users);
        return "admin_users.html";
    }

    @PostMapping("/usuario/{id}")
    public String editarUsuario(@PathVariable("id") Integer id, HttpServletRequest request, ModelMap modelo) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        ObjectMapper mapper = new JsonMapper();
        JsonNode json = mapper.readTree(body);
        Double salary = json.get("monthly_salary").asDouble();
        String role = json.get("role").asText();
        try {
            userService.changeRolToJournalist(id,role,salary );
            modelo.put("exito", "Has editado el usuario correctamente");
        } catch (MyException e) {
            modelo.put("error", e.getMessage());
        }

        return "redirect:/admin/usuarios";
    }
}
