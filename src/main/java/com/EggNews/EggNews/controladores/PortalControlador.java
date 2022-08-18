package com.EggNews.EggNews.controladores;

import com.EggNews.EggNews.entidades.Foto;
import com.EggNews.EggNews.entidades.Noticia;
import com.EggNews.EggNews.excepciones.MiException;
import com.EggNews.EggNews.servicios.FotoService;
import com.EggNews.EggNews.servicios.NoticiasService;
import java.io.IOException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/")
    public String registro(@RequestParam() String titulo, @RequestParam() String cuerpo, @RequestParam MultipartFile archivo, ModelMap modelo) throws IOException {

        try {
            noticiasService.crearNoticia(titulo, cuerpo, archivo);

            modelo.put("exito", "La noticia fue cargada con éxito!");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "index.html";

        }
        return "index.html";
    }
}
