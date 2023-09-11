package dev.lojavirtual.loja_virtual;

import dev.lojavirtual.loja_virtual.model.PessoaFisica;
import dev.lojavirtual.loja_virtual.model.PessoaJuridica;
import dev.lojavirtual.loja_virtual.repository.PessoaRepository;
import dev.lojavirtual.loja_virtual.service.PessoaUserService;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile("test")
@SpringBootTest(classes = LojaVirtualApplication.class)
public class TestePessoaUsuario extends TestCase {


    @Autowired
    private PessoaUserService pessoaUserService;

   @Autowired
   private PessoaRepository pessoaRepository;


    @Test
    public void testCadPessoaFisica() {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("07990336000198");
        pessoaJuridica.setNome("CRIO");
        pessoaJuridica.setEmail("teste@crio.com.br");
        pessoaJuridica.setTelefone("8535211515");
        pessoaJuridica.setInscEstadual("000");
        pessoaJuridica.setInscMunicipal("000");
        pessoaJuridica.setNomeFantasia("centro regional in tegrado de oncologia");
        pessoaJuridica.setRazaoSocial("crio");

		pessoaRepository.save(pessoaJuridica);


		PessoaFisica pessoaFisica = new PessoaFisica();

		pessoaFisica.setCpf("01457548399");
		pessoaFisica.setNome("Pedro Fellipe");
		pessoaFisica.setEmail("fellipe@crio.combr");
		pessoaFisica.setTelefone("85996500999");
		pessoaFisica.setEmpresa(pessoaFisica);
		pessoa
    }
}