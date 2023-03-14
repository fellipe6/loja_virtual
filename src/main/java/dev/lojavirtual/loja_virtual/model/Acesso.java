package dev.lojavirtual.loja_virtual.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="acesso")
@SequenceGenerator(name = "seq_acesso",sequenceName = "seq_acesso",allocationSize = 1,initialValue = 1)
public class Acesso implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_marca_produto")
    private Long id;

    private String descricao;

    @Override
    public String getAuthority() {
        return null;
    }
}
