package dev.lojavirtual.loja_virtual.service;

import dev.lojavirtual.loja_virtual.model.Acesso;
import dev.lojavirtual.loja_virtual.repository.AcessoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository acessoRepository;

    public Acesso save(Acesso acesso) {

        /*Qualquer tipo de validação*/

        return acessoRepository.save(acesso);
    }
}
