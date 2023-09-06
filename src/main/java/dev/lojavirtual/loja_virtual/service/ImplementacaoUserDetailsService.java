package dev.lojavirtual.loja_virtual.service;

import dev.lojavirtual.loja_virtual.model.Usuario;
import dev.lojavirtual.loja_virtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.HashSet;
import java.util.Set;

@Service
public class ImplementacaoUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findUserByLogin(username);/* Recebe o login pra consulta */

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não foi encontrado"  + usuario);

        }

        return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
    }

}

