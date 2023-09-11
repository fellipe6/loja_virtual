package dev.lojavirtual.loja_virtual.service;

import dev.lojavirtual.loja_virtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaUserService {
    @Autowired
    private UsuarioRepository usuarioRepository;

}
