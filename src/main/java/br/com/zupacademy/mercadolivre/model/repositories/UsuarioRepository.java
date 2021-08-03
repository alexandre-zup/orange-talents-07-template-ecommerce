package br.com.zupacademy.mercadolivre.model.repositories;

import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
