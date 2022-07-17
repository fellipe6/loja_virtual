package br.com.nazasoft.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.nazasoft.lojavirtual.entity.Acesso;
import br.com.nazasoft.lojavirtual.resource.AcessoResource;
import br.com.nazasoft.lojavirtual.service.AcessoService;

@SpringBootTest(classes = LojaVirtualApplication.class)
class LojaVirtualApplicationTests {

	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoResource acessoResource;
	
	@Autowired
	//private AcessoRepository acessoRepository;
	
	@Test
	public void testCadastroAcesso() {
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_FINANCEIRO");
		
		acessoResource.salvarAcesso(acesso);
	}

}
