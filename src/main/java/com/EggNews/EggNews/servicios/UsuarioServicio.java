package com.EggNews.EggNews.servicios;

import com.EggNews.EggNews.entidades.Usuario;
import com.EggNews.EggNews.enums.Rol;
import com.EggNews.EggNews.excepciones.MiException;
import com.EggNews.EggNews.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrar(String nombre, String email, String password, String password2) throws MiException {

        validar(nombre, email, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(nombre);
        usuario.setEmail(email);
        
        usuario.setAlta(new Date());

        usuario.setPassword(new BCryptPasswordEncoder().encode(password));

        usuario.setRol(Rol.USER);

        usuarioRepositorio.save(usuario);
    }

    private void validar(String nombre, String email, String password, String password2) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("Por favor indique un nombre");
        }
        if (email == null || email.isEmpty()) {
            throw new MiException("Por favor indique un email");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new MiException("Por favor indique una contraseña que contenga mas de 5 digitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraserñas ingresadas deben ser iguales");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarPorMail(email);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }
    }
}
