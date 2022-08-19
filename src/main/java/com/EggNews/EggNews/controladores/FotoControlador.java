package com.EggNews.EggNews.controladores;

import com.EggNews.EggNews.entidades.Noticia;
import com.EggNews.EggNews.excepciones.MiException;
import com.EggNews.EggNews.servicios.NoticiasService;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/foto")
@Slf4j
public class FotoControlador {

    @Autowired
    private NoticiasService noticiasService;

    @GetMapping("/noticias")
    public ResponseEntity<byte[]> fotoNoticia(@RequestParam Long id) {
        try {
            Noticia noticia = noticiasService.buscarPorId(id);
            if (noticia.getFoto() == null) {
                throw new MiException("La noticia no tiene foto");
            }
            byte[] foto = noticia.getFoto().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (MiException ex) {
            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
