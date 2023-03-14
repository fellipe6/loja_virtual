package dev.lojavirtual.loja_virtual.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaFisica extends Pessoa{

    private static final long serialVersionUID = 1l;

    @Column(nullable = false)
    private String cpf;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaFisica that = (PessoaFisica) o;
        return getCpf().equals(that.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf());
    }
}
