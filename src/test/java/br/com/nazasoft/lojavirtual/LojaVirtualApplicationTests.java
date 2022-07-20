package br.com.nazasoft.lojavirtual;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.nazasoft.lojavirtual.entity.Acesso;
import br.com.nazasoft.lojavirtual.repository.AcessoRepository;
import br.com.nazasoft.lojavirtual.resource.AcessoResource;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualApplication.class)
class LojaVirtualApplicationTests extends TestCase {

	@Autowired
	private AcessoResource acessoResource;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@Test
	public void testCadastroAcesso() {
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_FINANCEIRO");
		assertEquals(true, acesso.getId() == null);
		//Gravou no banco
		acesso = acessoResource.salvarAcesso(acesso).getBody();
		
		assertEquals(true, acesso.getId() > 0);
		//valida os dados
		assertEquals("ROLE_FINANCEIRO", acesso.getDescricao());
		
		//Teste de carregamento
		Acesso acesso2  = acessoRepository.findById(acesso.getId()).get();
		
		assertEquals(acesso.getId(), acesso2.getId());
		
		//Teste de delete
		acessoRepository.deleteById(acesso2.getId());
		acessoRepository.flush();// roda o sql no banco
		
		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null); 
		assertEquals(true, acesso3 == null);
		
		//Teste de query
		
		acesso = new Acesso();
			
		acesso.setDescricao("ROLE_ALUNO");
		
		acesso = acessoResource.salvarAcesso(acesso).getBody();
		
		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim().toUpperCase());
		
		assertEquals(1, acessos.size());
		
		//acessoRepository.deleteById(acesso.getId());
	}

}
