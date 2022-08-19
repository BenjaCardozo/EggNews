package com.EggNews.EggNews.controladores;

import com.EggNews.EggNews.entidades.Foto;
import com.EggNews.EggNews.entidades.Noticia;
import com.EggNews.EggNews.servicios.FotoService;
import com.EggNews.EggNews.servicios.NoticiasService;
import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class PortalControlador {

    @Autowired
    NoticiasService noticiasService;
    
    @Autowired
    FotoService fotoService;

    @GetMapping("/")
    public String index(Model model) {
        ArrayList<Noticia> noticias = (ArrayList<Noticia>) noticiasService.listarNoticias();
        ArrayList<Foto> fotos = (ArrayList<Foto>) fotoService.listarFotos();
        log.info("hola");
        model.addAttribute("noticia", noticias);
        model.addAttribute("foto", fotos);
        return "index.html";
    }
}
