package dev.lojavirtual.loja_virtual.repository;

import dev.lojavirtual.loja_virtual.model.PessoaJuridica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaJuridica, Long> {


    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    public PessoaJuridica existeCnpjCadastrado(String cnpj);


}
