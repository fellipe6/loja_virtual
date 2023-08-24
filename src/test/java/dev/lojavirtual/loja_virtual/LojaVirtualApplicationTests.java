package dev.lojavirtual.loja_virtual;


import dev.lojavirtual.loja_virtual.controller.AcessoController;
import dev.lojavirtual.loja_virtual.model.Acesso;
import dev.lojavirtual.loja_virtual.repository.AcessoRepository;
import dev.lojavirtual.loja_virtual.service.AcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LojaVirtualApplicationTests {


	@Autowired
	private   AcessoController acessoController;
	@Test
	public void testCadastraAcesso() {
	Acesso acesso = new Acesso();
	acesso.setDescricao("ROLE_TESTER");
		acessoController.salvarAcesso(acesso);
	}

}
