package br.com.zupacademy.mercadolivre.model.repositories;

import br.com.zupacademy.mercadolivre.model.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
