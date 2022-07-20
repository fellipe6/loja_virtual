package br.com.nazasoft.lojavirtual.security;

import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener{

	public void configure(WebSecurity web)throws Exception{
		web.ignoring().antMatchers(HttpMethod.GET,"/salvarAcesso","/deletaAcesso")
		.antMatchers(HttpMethod.POST,"/salvarAcesso","/deletaAcesso");
		//Ignorando a url no momento para não autenticar
	}
}
