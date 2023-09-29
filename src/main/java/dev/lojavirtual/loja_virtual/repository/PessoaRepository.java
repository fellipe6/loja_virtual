package dev.lojavirtual.loja_virtual.repository;

import dev.lojavirtual.loja_virtual.model.PessoaJuridica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaRepository extends CrudRepository<PessoaJuridica, Long> {


    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    public PessoaJuridica existeCnpjCadastrado(String cnpj);

    @Query(value = "select pj from PessoaJuridica pj where pj.inscEstadual = ?1")
    public PessoaJuridica existeInscEstadualCadastro(String inscEstadual);

    @Query(value = "select pf from PessoaFisica pj where pf.cpf = ?1")
    public PessoaJuridica existeCpfCadastrado(String cnpj);
}
