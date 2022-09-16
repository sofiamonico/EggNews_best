package com.egg.EggNewsintel.web.controller;

import com.egg.EggNewsintel.domain.New;
import com.egg.EggNewsintel.domain.exception.MyException;
import com.egg.EggNewsintel.domain.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("/noticia")
public class NewController {
    @Autowired
    private NewService newService;
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_PERIODISTA')")
    @GetMapping("/agregar")
    public String agregar(){

        return "create_new.html";
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_PERIODISTA')")
    @PostMapping("/agregado")
    public String save(@RequestParam Integer id, String titulo, String cuerpo,
                                  MultipartFile imagen, ModelMap modelo ){

        try {
            newService.save(titulo, cuerpo, imagen, id);

            modelo.put("exito", "La noticia se ha cargado con exito");

        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "create_new.html";
        }

        return "redirect:/periodista";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_PERIODISTA')")
    @GetMapping("/editar/{id}")
    public String editarNoticia(@PathVariable Integer id, ModelMap modelo){
        Optional<New> respuesta = newService.getNoticia(id);
        modelo.put("noticia", respuesta.get());

        return "edit_new.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_PERIODISTA')")
    @PostMapping("/editar/{id}")
    public String editarNoticia(@PathVariable String id, String titulo, String cuerpo, MultipartFile imagen, ModelMap modelo){
        try {
            newService.modificarNoticia(Integer.parseInt(id), titulo, cuerpo, imagen);
            return "redirect:/index";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("noticia", newService.getNoticia(Integer.parseInt(id)));
            return "edit_new.html";
        }

    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_PERIODISTA')")
    @PostMapping("/eliminar/{id}")
    public String eliminarNoticia(@PathVariable String id){

        newService.delete(Integer.parseInt(id));

        return "redirect:/administrar";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_USUARIO', 'ROLE_PERIODISTA')")
    @GetMapping("/{id}")
    public String mostrarNoticia(@PathVariable String id, ModelMap modelo){
        Optional<New> respuesta = newService.getNoticia(Integer.parseInt(id));
        modelo.put("noticia", respuesta.get());
        return "view_new.html";
    }
}
