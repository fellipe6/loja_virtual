package dev.lojavirtual.loja_virtual.repository;

import dev.lojavirtual.loja_virtual.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    @Query(value="select u from Usuario u where u.login = ?1")
    Usuario findUserByLogin(String login);
}
