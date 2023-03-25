package dev.lojavirtual.loja_virtual.controller;

import dev.lojavirtual.loja_virtual.model.Acesso;
import dev.lojavirtual.loja_virtual.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @PostMapping("/salvarAcesso")
    public Acesso salvarAcesso(Acesso acesso) {

        return acessoService.save(acesso);
    }
}
