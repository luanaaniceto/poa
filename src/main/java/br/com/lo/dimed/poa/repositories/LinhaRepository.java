package br.com.lo.dimed.poa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.lo.dimed.poa.entities.Linha;

public interface LinhaRepository extends JpaRepository<Linha, Long> {

	List<Linha> findByNomeIgnoreCase(@Param("nome") String nome);

	Linha findByIdLinha(@Param("idLinha") Long idLinha);
}