package dev.lojavirtual.loja_virtual.controller;

import dev.lojavirtual.loja_virtual.ExceptionLJJava;
import dev.lojavirtual.loja_virtual.model.PessoaFisica;
import dev.lojavirtual.loja_virtual.model.PessoaJuridica;
import dev.lojavirtual.loja_virtual.repository.PessoaRepository;
import dev.lojavirtual.loja_virtual.service.PessoaUserService;
import dev.lojavirtual.loja_virtual.util.ValidaCNPJ;
import dev.lojavirtual.loja_virtual.util.ValidaCPF;
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
            throw new ExceptionLJJava("Pessoa juridica não pode ser null!");
        }
        if(pessoaJuridica.getId() == null && pessoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj())!=null){
            throw new ExceptionLJJava("Pessoa juridica já cadastrada CNPJ:" + pessoaJuridica.getCnpj());
        }
        if(pessoaJuridica.getId() == null && pessoaRepository.existeInscEstadualCadastro(pessoaJuridica.getInscEstadual())!=null){
            throw new ExceptionLJJava("Pessoa juridica já cadastrada com essa inscrição estadual:" + pessoaJuridica.getInscEstadual());
        }

        if(!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())){
            throw new ExceptionLJJava("CNPJ : " + pessoaJuridica.getCnpj() + "está inválido!" );
        }
        pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);
        return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value="/salvarPf")
    public ResponseEntity<PessoaFisica> salvarPf(@RequestBody PessoaFisica pessoaFisica) throws ExceptionLJJava {

        if(pessoaFisica == null){
            throw new ExceptionLJJava("Pessoa fisica não pode ser null!");
        }
        if(pessoaFisica.getId() == null && pessoaRepository.existeCpfCadastrado(pessoaFisica.getCpf())!=null){
            throw new ExceptionLJJava("Pessoa física já cadastrada com esse CPF: " + pessoaFisica.getCpf());
        }

        if(!ValidaCPF.isCPF(pessoaFisica.getCpf())){
            throw new ExceptionLJJava("CPF : " + pessoaFisica.getCpf() + "está inválido!" );
        }
        pessoaFisica = pessoaUserService.salvarPessoaFisica(pessoaFisica);
        return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
    }
}
