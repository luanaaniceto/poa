package br.com.lo.dimed.poa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lo.dimed.poa.entities.ClienteLinha;

public interface ClienteLinhaRepository extends JpaRepository<ClienteLinha, Long> {
	public List<ClienteLinha> findByIdCliente(Long id);
}
