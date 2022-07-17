package br.com.nazasoft.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nazasoft.lojavirtual.entity.Acesso;
import br.com.nazasoft.lojavirtual.repository.AcessoRepository;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepository;

	public Acesso  save(Acesso acesso) {
		return acessoRepository.save(acesso);
	}
}
