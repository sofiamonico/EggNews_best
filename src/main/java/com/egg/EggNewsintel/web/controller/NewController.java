package com.egg.EggNewsintel.web.controller;

import com.egg.EggNewsintel.domain.exception.MyException;
import com.egg.EggNewsintel.domain.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/noticia")
public class NewController {
    @Autowired
    private NewService newService;

    @GetMapping("/agregar")
    public String agregar(){

        return "create_new.html";
    }

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


    @GetMapping("/editar/{id}")
    public String editarNoticia(@PathVariable Integer id, ModelMap modelo){
        modelo.put("noticia", newService.getNoticia(id));

        return "edit_view.html";
    }

    @PostMapping("/editar/{id}")
    public String editarNoticia(@PathVariable String id, String titulo, String cuerpo, MultipartFile imagen, ModelMap modelo){
        try {
            newService.modificarNoticia(Integer.parseInt(id), titulo, cuerpo, imagen);
            return "redirect:/administrar";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("noticia", newService.getNoticia(Integer.parseInt(id)));
            return "edit_new.html";
        }

    }

    @PostMapping("/eliminar/{id}")
    public String eliminarNoticia(@PathVariable String id){

        newService.delete(Integer.parseInt(id));

        return "redirect:/administrar";
    }

    @GetMapping("/{id}")
    public String mostrarNoticia(@PathVariable String id, ModelMap modelo){
        modelo.put("noticia", newService.getNoticia(Integer.parseInt(id)));
        return "view_new.html";
    }
}
