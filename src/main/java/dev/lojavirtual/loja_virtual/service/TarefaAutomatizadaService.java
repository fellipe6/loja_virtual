package dev.lojavirtual.loja_virtual.service;

import dev.lojavirtual.loja_virtual.model.Usuario;
import dev.lojavirtual.loja_virtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
@Service
public class TarefaAutomatizadaService {

    @Autowired
    UsuarioRepository usuarioRepository;
   @Autowired
    private ServiceSendEmail serviceSendEmail;

    @Scheduled(initialDelay = 2000, fixedDelay =86400000 )//Roda a  cada 24 horas
    //@Scheduled(cron = "0 0 11 * * *", zone = "America/Sao_Paulo")//Rodas as 1 em horário de brasília
    public void notificarUserTrocaSenha() throws MessagingException, UnsupportedEncodingException, InterruptedException {
        List<Usuario> usuarios = usuarioRepository.usuarioSenhaVencida();

        for(Usuario usuario : usuarios){
            StringBuilder msg = new StringBuilder();
            msg.append("Olá, ").append(usuario.getPessoa().getNome()).append("<br/>");
            msg.append("Está na hora de trocar sua senha, já passou 90 doas de validade").append("<br/>");
            msg.append("Troque sua senha JDEV");
            serviceSendEmail.enviarEmailHtml("Troca de senha", msg.toString(),usuario.getLogin());

            Thread.sleep(3000);
        }

    }


}
