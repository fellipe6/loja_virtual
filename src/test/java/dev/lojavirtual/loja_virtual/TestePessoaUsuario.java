package dev.lojavirtual.loja_virtual;

import dev.lojavirtual.loja_virtual.controller.PessoaController;
import dev.lojavirtual.loja_virtual.enums.TipoEndereco;
import dev.lojavirtual.loja_virtual.model.Endereco;
import dev.lojavirtual.loja_virtual.model.PessoaFisica;
import dev.lojavirtual.loja_virtual.model.PessoaJuridica;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;

@Profile("test")
@SpringBootTest(classes = LojaVirtualApplication.class)
public class TestePessoaUsuario extends TestCase {


    @Autowired
    private PessoaController pessoaController;


    @Test
    public void testCadPessoaFisica() throws ExceptionLJJava {

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
        pessoaJuridica.setNome("Pedro fellipe");
        pessoaJuridica.setEmail("testesalvarpj22@gmail.com");
        pessoaJuridica.setTelefone("45999795800");
        pessoaJuridica.setInscEstadual("65556565656665");
        pessoaJuridica.setInscMunicipal("55554565656565");
        pessoaJuridica.setNomeFantasia("54556565665");
        pessoaJuridica.setRazaoSocial("4656656566");

        Endereco endereco1 = new Endereco();
        endereco1.setBairro("teste messejana");
        endereco1.setCep("60336045");
        endereco1.setComplemento("teste");
        endereco1.setEmpresa(pessoaJuridica);
        endereco1.setNumero("163");
        endereco1.setRuaLogra("Padre pedro de alencar");
        endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
        endereco1.setUf("CE");
        endereco1.setCidade("FORTALEZA");

        Endereco endereco2 = new Endereco();
        endereco2.setBairro("teste messejana2");
        endereco2.setCep("60336045");
        endereco2.setComplemento("teste2");
        endereco2.setEmpresa(pessoaJuridica);
        endereco2.setNumero("163A");
        endereco2.setRuaLogra("Padre pedro de alencar");
        endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
        endereco2.setUf("CE");
        endereco2.setCidade("FORTALEZA");
        pessoaJuridica.getEnderecos().add(endereco1);
        pessoaJuridica.getEnderecos().add(endereco2);

        pessoaJuridica = pessoaController.salvarPj(pessoaJuridica).getBody();
        assertEquals(true,pessoaJuridica.getId() > 0);

        for(Endereco endereco : pessoaJuridica.getEnderecos()){
            assertEquals(true, endereco.getId() > 0);
        }
        assertEquals(2,pessoaJuridica.getEnderecos().size());
    }
}