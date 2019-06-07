package br.com.lo.dimed.poa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.lo.dimed.poa.entities.Posicionamento;

public class PosicionamentoDTO {
	@JsonProperty
	protected Double lat;
	@JsonProperty
	protected Double lng;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Posicionamento gerarPosicionamento(Long pIdItinerario, Double pLatitude, Double pLongitude) {
		return new Posicionamento(pIdItinerario, pLatitude, pLongitude);
	}
}
