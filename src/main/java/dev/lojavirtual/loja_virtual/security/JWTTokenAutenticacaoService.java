package dev.lojavirtual.loja_virtual.security;

import dev.lojavirtual.loja_virtual.ApplicationContextLoad;
import dev.lojavirtual.loja_virtual.model.Usuario;
import dev.lojavirtual.loja_virtual.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//Criar a autenticação e retornar  autenticação com JWT
@Service
@Component
public class JWTTokenAutenticacaoService {

    //Token de validade de 11 dias
    private static final long EXPIRATION_TIME = 959990000;

    private static final String SECRET = "CrioRad1";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    //Gera o token e retorna pro cliente
    public void addAuthentication(HttpServletResponse response, String username) throws Exception {
        //Montagem do token
        String JWT = Jwts.builder().//chama o gerador de token
                setSubject(username).//Adiciona usuário
                setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
                signWith(SignatureAlgorithm.HS512, SECRET).compact(); //tempo de expiração

        String token = TOKEN_PREFIX + " " + JWT;
        //gera resposta pra tela e para o cliente ou outra api, navegador, aplicativo, etc
        response.addHeader(HEADER_STRING, token);

        liberacaoCors(response);
        //usado para testes no postman
        response.getWriter().write("{\"Authorization\": \"" + token + "\" }");

    }
    /*Retorna o usuário validado com token ou caso nao seja valido retona null*/

    public Authentication getAuthetication(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader(HEADER_STRING);

        if (token != null) {

            String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();

            /*Faz a validacao do token do usuário na requisicao e obtem o USER*/
            String user = Jwts.parser().
                    setSigningKey(SECRET)
                    .parseClaimsJws(tokenLimpo)
                    .getBody().getSubject(); /*ADMIN ou Alex*/

            if (user != null) {

                Usuario usuario = ApplicationContextLoad.
                        getApplicationContext().
                        getBean(UsuarioRepository.class).findUserByLogin(user);

                if (usuario != null) {
                    return new UsernamePasswordAuthenticationToken(
                            usuario.getLogin(),
                            usuario.getSenha(),
                            usuario.getAuthorities());
                }

            }

        }

        liberacaoCors(response);
        return null;
    }


        //Liberação contra erro  de CORS no navegador
    private void liberacaoCors(HttpServletResponse response) {


        if (response.getHeader("Access-Control-Allow-Origin") == null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        }


        if (response.getHeader("Access-Control-Allow-Headers") == null) {
            response.addHeader("Access-Control-Allow-Headers", "*");
        }


        if (response.getHeader("Access-Control-Request-Headers") == null) {
            response.addHeader("Access-Control-Request-Headers", "*");
        }

        if (response.getHeader("Access-Control-Allow-Methods") == null) {
            response.addHeader("Access-Control-Allow-Methods", "*");
        }

    }

}