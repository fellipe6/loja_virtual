package dev.lojavirtual.loja_virtual.controller;

import dev.lojavirtual.loja_virtual.model.Acesso;
import dev.lojavirtual.loja_virtual.repository.AcessoRepository;
import dev.lojavirtual.loja_virtual.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class AcessoController {


    private final AcessoService acessoService;

    @Autowired
    public AcessoController(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    @Autowired
    private AcessoRepository acessoRepository;

    @ResponseBody /*Pode dar um retorno da API*/
    @PostMapping(value = "/salvarAcesso") /*Mapeando a url para receber JSON*/
    public Acesso salvarAcesso(@RequestBody Acesso acesso) { /*Recebe o JSON e converte pra Objeto*/

        //Acesso acessoSalvo = acessoService.save(acesso);

        return acessoService.save(acesso);
    }


    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "/deleteAcesso") /*Mapeando a url para receber JSON*/
    public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) { /*Recebe o JSON e converte pra Objeto*/

        acessoRepository.deleteById(acesso.getId());

        return new ResponseEntity("Acesso Removido", HttpStatus.OK);
    }
}