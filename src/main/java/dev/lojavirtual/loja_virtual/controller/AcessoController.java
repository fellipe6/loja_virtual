package dev.lojavirtual.loja_virtual.controller;

import dev.lojavirtual.loja_virtual.model.Acesso;
import dev.lojavirtual.loja_virtual.repository.AcessoRepository;
import dev.lojavirtual.loja_virtual.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @Autowired
    private AcessoRepository acessoRepository;

    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "/salvarAcesso") /*Mapeando a url para receber JSON*/
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) { /*Recebe o JSON e converte pra Objeto*/

        Acesso acessoSalvo = acessoService.save(acesso);

        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
    }


    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "/deleteAcesso") /*Mapeando a url para receber JSON*/
    public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) { /*Recebe o JSON e converte pra Objeto*/

        acessoRepository.deleteById(acesso.getId());

        return new ResponseEntity("Acesso Removido", HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "/deleteAcessoPorId/{id}")
    public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {

        acessoRepository.deleteById(id);

        return new ResponseEntity("Acesso Removido", HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping(value = "/obterAcesso/{id}")
    public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) {

        Acesso acesso = acessoRepository.findById(id).get();

        return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping(value = "/buscarPorDesc/{desc}")
    public ResponseEntity<List<Acesso>> buscarPorDesc(@PathVariable("desc") String desc) {

        List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc);

        return new ResponseEntity<List<Acesso>>(acesso, HttpStatus.OK);
    }

}