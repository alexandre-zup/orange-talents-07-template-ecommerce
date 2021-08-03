package br.com.zupacademy.mercadolivre.model.repositories;

import br.com.zupacademy.mercadolivre.model.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
