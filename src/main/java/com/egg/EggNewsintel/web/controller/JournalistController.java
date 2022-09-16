package com.egg.EggNewsintel.web.controller;
import com.egg.EggNewsintel.domain.Journalist;
import com.egg.EggNewsintel.domain.New;
import com.egg.EggNewsintel.domain.service.JournalistService;
import com.egg.EggNewsintel.domain.service.NewService;

import com.egg.EggNewsintel.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/periodista")
public class JournalistController {
    @Autowired
    private NewService newService;
    @Autowired
    private JournalistService journalistService;

    @GetMapping()
    public String indexJournalist(){
        return "periodista.html";
    }

    @GetMapping("/noticias")
    public String myNews(ModelMap modelo, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        Optional<Journalist> respuesta = journalistService.findPeriodistaByNombreUsuario(usuario.getNombreUsuario());
        if(respuesta.isPresent()){
            List<New> news = respuesta.get().getNews();
            modelo.addAttribute("news", news);
        }

        return "periodista_news.html";
    }


}
