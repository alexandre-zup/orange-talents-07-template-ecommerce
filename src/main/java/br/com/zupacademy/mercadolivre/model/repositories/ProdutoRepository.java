package br.com.zupacademy.mercadolivre.model.repositories;

import br.com.zupacademy.mercadolivre.model.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
