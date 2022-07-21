package br.com.nazasoft.lojavirtual.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.nazasoft.lojavirtual.entity.Acesso;
import br.com.nazasoft.lojavirtual.repository.AcessoRepository;
import br.com.nazasoft.lojavirtual.service.AcessoService;

@Controller
@RestController
public class AcessoResource {

	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@ResponseBody	
	@PostMapping(value ="/salvarAcesso")
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
		Acesso acessoSalvo = acessoService.save(acesso);
		return new ResponseEntity<Acesso>(acessoSalvo,HttpStatus.OK);
	}
	
	@ResponseBody	
	@PostMapping(value ="/deletaAcesso")
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) {
	    acessoRepository.deleteById(acesso.getId());
		return new ResponseEntity("Acesso removido com sucesso!",HttpStatus.OK);
	}
	
	@ResponseBody	
	@DeleteMapping(value ="/deletaAcessoPorId/{id}")
	public ResponseEntity<?> deletaAcessoPorId(@PathVariable("id")Long id) {
	    acessoRepository.deleteById(id);
		return new ResponseEntity("Acesso removido com sucesso!",HttpStatus.OK);
	}
	@ResponseBody	
	@GetMapping(value ="/obterAcesso/{id}")
	public ResponseEntity<Acesso> obterAcesso(@PathVariable("id")Long id) {
	  Acesso acesso =   acessoRepository.findById(id).get();
		return new ResponseEntity(acesso,HttpStatus.OK);
	}
	
	@ResponseBody	
	@GetMapping(value ="/buscarPorDescricao/{desc}")
	public ResponseEntity<List<Acesso>> buscarPorDescricao(@PathVariable("desc")String desc) {
	 List<Acesso> acesso  = acessoRepository.buscarAcessoDesc(desc);
		return new ResponseEntity<List<Acesso>>(acesso,HttpStatus.OK);
	}
}
