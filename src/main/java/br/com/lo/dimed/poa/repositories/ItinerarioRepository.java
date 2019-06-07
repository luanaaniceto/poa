package br.com.lo.dimed.poa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lo.dimed.poa.entities.Itinerario;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {
	Itinerario findByidlinha(@Param("idlinha") Long idlinha);

	@Query("select i from Itinerario i where i.id = :id")
	Itinerario getItinerario(@Param("id") Long id);
}