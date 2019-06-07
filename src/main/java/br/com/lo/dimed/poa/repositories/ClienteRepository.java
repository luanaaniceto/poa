package br.com.lo.dimed.poa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lo.dimed.poa.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
