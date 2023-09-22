package dev.lojavirtual.loja_virtual.controller;

import dev.lojavirtual.loja_virtual.ExceptionLJJava;
import dev.lojavirtual.loja_virtual.model.PessoaJuridica;
import dev.lojavirtual.loja_virtual.repository.PessoaRepository;
import dev.lojavirtual.loja_virtual.service.PessoaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaUserService pessoaUserService;
    @ResponseBody
    @PostMapping(value="/salvarPj")
    public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica) throws ExceptionLJJava {

        if(pessoaJuridica == null){
            throw new ExceptionLJJava("Pessoa juridica não pode ser null");
        }
        if(pessoaJuridica.getId() == null && pessoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj())!=null){
            throw new ExceptionLJJava("Pessoa juridica já cadastrada CNPJ:" + pessoaJuridica.getCnpj());
        }
        pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);
        return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
    }

}
