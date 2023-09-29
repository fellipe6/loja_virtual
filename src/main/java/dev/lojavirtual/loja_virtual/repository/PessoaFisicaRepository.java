package dev.lojavirtual.loja_virtual.repository;

import dev.lojavirtual.loja_virtual.model.PessoaFisica;
import dev.lojavirtual.loja_virtual.model.PessoaJuridica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {


}
