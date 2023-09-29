package dev.lojavirtual.loja_virtual.service;

import dev.lojavirtual.loja_virtual.model.PessoaFisica;
import dev.lojavirtual.loja_virtual.model.PessoaJuridica;
import dev.lojavirtual.loja_virtual.model.Usuario;
import dev.lojavirtual.loja_virtual.repository.PessoaFisicaRepository;
import dev.lojavirtual.loja_virtual.repository.PessoaRepository;
import dev.lojavirtual.loja_virtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PessoaUserService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pesssoaRepository;

    @Autowired
    private PessoaFisicaRepository  pessoaFisicaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ServiceSendEmail serviceSendEmail;

    public PessoaJuridica salvarPessoaJuridica(PessoaJuridica juridica) {

        //juridica = pesssoaRepository.save(juridica);

        for (int i = 0; i< juridica.getEnderecos().size(); i++) {
            juridica.getEnderecos().get(i).setPessoa(juridica);
            juridica.getEnderecos().get(i).setEmpresa(juridica);
        }

        juridica = pesssoaRepository.save(juridica);

        Usuario usuarioPj = usuarioRepository.findUserByPessoa(juridica.getId(), juridica.getEmail());

        if (usuarioPj == null) {

            String constraint = usuarioRepository.consultaConstraintAcesso();
            if (constraint != null) {
                jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
            }

            usuarioPj = new Usuario();
            usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
            usuarioPj.setEmpresa(juridica);
            usuarioPj.setPessoa(juridica);
            usuarioPj.setLogin(juridica.getEmail());

            String senha = "" + Calendar.getInstance().getTimeInMillis();
            String senhaCript = new BCryptPasswordEncoder().encode(senha);

            usuarioPj.setSenha(senhaCript);

            usuarioPj = usuarioRepository.save(usuarioPj);

            usuarioRepository.insereAcessoUser(usuarioPj.getId());
            //Salva como ROLE_ADMIN dinamicamente
            usuarioRepository.insereAcessoUserPj(usuarioPj.getId(),"ROLE_ADMIN");

            StringBuilder mensagemHtml = new StringBuilder();

            mensagemHtml.append("<b>Segue abaixo seus dados de acesso para a loja virtual</b>");
            mensagemHtml.append("<b>Login: </b>"+juridica.getEmail()+"</b><br/>");
            mensagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
            mensagemHtml.append("Obrigado!");

            try {
                serviceSendEmail.enviarEmailHtml("Acesso Gerado para Loja Virtual", mensagemHtml.toString() , juridica.getEmail());
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        return juridica;

    }


    public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {
        //juridica = pesssoaRepository.save(juridica);

        for (int i = 0; i< pessoaFisica.getEnderecos().size(); i++) {
            pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
            pessoaFisica.getEnderecos().get(i).setEmpresa(pessoaFisica);
        }

        pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

        Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoaFisica.getId(), pessoaFisica.getEmail());

        if (usuarioPj == null) {

            String constraint = usuarioRepository.consultaConstraintAcesso();
            if (constraint != null) {
                jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
            }

            usuarioPj = new Usuario();
            usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
            usuarioPj.setEmpresa(pessoaFisica);
            usuarioPj.setPessoa(pessoaFisica);
            usuarioPj.setLogin(pessoaFisica.getEmail());

            String senha = "" + Calendar.getInstance().getTimeInMillis();
            String senhaCript = new BCryptPasswordEncoder().encode(senha);

            usuarioPj.setSenha(senhaCript);

            usuarioPj = usuarioRepository.save(usuarioPj);

            usuarioRepository.insereAcessoUser(usuarioPj.getId());

            StringBuilder mensagemHtml = new StringBuilder();

            mensagemHtml.append("<b>Segue abaixo seus dados de acesso para a loja virtual</b>");
            mensagemHtml.append("<b>Login: </b>"+pessoaFisica.getEmail()+"</b><br/>");
            mensagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
            mensagemHtml.append("Obrigado!");

            try {
                serviceSendEmail.enviarEmailHtml("Acesso Gerado para Loja Virtual", mensagemHtml.toString() , pessoaFisica.getEmail());
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        return pessoaFisica;
    }
}