package com.EggNews.EggNews.controladores;

import com.EggNews.EggNews.entidades.Foto;
import com.EggNews.EggNews.entidades.Noticia;
import com.EggNews.EggNews.excepciones.MiException;
import com.EggNews.EggNews.servicios.FotoService;
import com.EggNews.EggNews.servicios.NoticiasService;
import com.EggNews.EggNews.servicios.UsuarioServicio;
import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@Slf4j
public class PortalControlador {

    @Autowired
    NoticiasService noticiasService;

    @Autowired
    FotoService fotoService;

    @Autowired
    UsuarioServicio usuarioService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("exito", "Usuario registrado correctamente.");
        return "login";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombreUsuario, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo) throws MiException {

        try {
            usuarioService.registrar(nombreUsuario, email, password, password2);
            return "redirect:/";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombreUsuario", nombreUsuario);
            modelo.put("email", email);
            return "registro.html";

        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o contraseña invalidos");
        }
        
        return "login.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(ModelMap model){
        ArrayList<Noticia> noticias = (ArrayList<Noticia>) noticiasService.listarNoticias();
        ArrayList<Foto> fotos = (ArrayList<Foto>) fotoService.listarFotos();
        log.info("hola");
        model.addAttribute("noticia", noticias);
        model.addAttribute("foto", fotos);
        return "inicio.html";
    }

}
