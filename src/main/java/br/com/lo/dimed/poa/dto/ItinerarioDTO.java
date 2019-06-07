package br.com.lo.dimed.poa.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.lo.dimed.poa.entities.Itinerario;

public class ItinerarioDTO {

	@JsonProperty
	protected Long idlinha;
	@JsonProperty
	protected String nome;
	@JsonProperty
	protected String codigo;

	public List<PosicionamentoDTO> posicionamentoDTO;

	public Long getIdlinha() {
		return idlinha;
	}

	public void setIdlinha(Long Idlinha) {
		this.idlinha = Idlinha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<PosicionamentoDTO> getPosicionamentoDTO() {
		return posicionamentoDTO;
	}

	public void setPosicionamentoDTO(List<PosicionamentoDTO> posicionamentoDTO) {
		this.posicionamentoDTO = posicionamentoDTO;
	}

	public Itinerario gerarItinerario(Long pIdlinha, String pNome, String pCodigo) {
		return new Itinerario(pIdlinha, pNome, pCodigo);
	}

}
