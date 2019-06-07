package br.com.lo.dimed.poa.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lo.dimed.poa.entities.Posicionamento;

public interface PosicionamentoRepository extends JpaRepository<Posicionamento, Long> {
	Posicionamento findByLatitudeAndLongitude(@Param("latitude") String latitude, @Param("longitude") String longitude);

	Posicionamento findByIdItinerario(@Param("idItinerario") Long idItinerario);

	// 57.2957795 = 180graus equivalente a raio
	@Query("SELECT  distinct idItinerario," + "(6371 * acos(" + "cos(( :latitude/57.2957795 ) ) "
			+ "* cos(( latitude/ 57.2957795 ) ) " + "* cos(( longitude/ 57.2957795 ) - ( :longitude/ 57.2957795 ) ) "
			+ "+ sin( ( :latitude/ 57.2957795 )) " + "* sin( ( latitude/ 57.2957795 ) ) )) as latitude"
			+ " from Posicionamento where latitude < :raio ")
	ArrayList<?> getLatitudeLongitudeRaio(@Param("latitude") Double latitude, @Param("longitude") Double longitude,
			@Param("raio") Double raio);

	// 57.2957795 = 180graus equivalente a raio
	@Query("SELECT distinct p.idItinerario," + "(6371 * acos(" + "cos(( :latitude/57.2957795 ) ) " + "* cos(( p.latitude/ 57.2957795 ) ) "
			+ "* cos(( p.longitude/ 57.2957795 ) - ( :longitude/ 57.2957795 ) ) " + "+ sin( ( :latitude/ 57.2957795 )) "
			+ "* sin( ( p.latitude/ 57.2957795 ) ) )) as latitude" + " from Posicionamento p "
			+ " inner join Itinerario i " + " on (i.id = p.idItinerario) " + " inner join Linha l "
			+ " on(l.idLinha = i.idlinha) " + " where latitude < :raio " + " and l.idLinha = :idLinha")
	ArrayList<?> getLatitudePorLinha(@Param("latitude") Double latitude, @Param("longitude") Double longitude,
			@Param("raio") Double raio, @Param("idLinha") Long idLinha);
}