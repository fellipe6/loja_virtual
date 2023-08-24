package dev.lojavirtual.loja_virtual.service;

import dev.lojavirtual.loja_virtual.model.Acesso;
import dev.lojavirtual.loja_virtual.repository.AcessoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {


    private final AcessoRepository acessoRepository;

    @Autowired
    public AcessoService(AcessoRepository acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    public Acesso save(Acesso acesso) {

        /*Qualquer tipo de validação*/

        return acessoRepository.save(acesso);
    }
}
