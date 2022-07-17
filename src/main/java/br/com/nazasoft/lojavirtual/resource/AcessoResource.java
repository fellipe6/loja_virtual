package br.com.nazasoft.lojavirtual.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.nazasoft.lojavirtual.entity.Acesso;
import br.com.nazasoft.lojavirtual.service.AcessoService;

@Controller
public class AcessoResource {

	@Autowired
	private AcessoService acessoService;
	
	@PostMapping("/salvarAcesso")
	public Acesso salvarAcesso(Acesso acesso) {
		return acessoService.save(acesso);
	}
}
