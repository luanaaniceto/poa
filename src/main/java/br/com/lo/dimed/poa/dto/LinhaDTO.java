package br.com.lo.dimed.poa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.lo.dimed.poa.entities.Linha;

public class LinhaDTO {

	@JsonProperty
	public Long id;
	@JsonProperty
	public String nome;
	@JsonProperty
	public String codigo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Linha gerarLinhas(Long pIdLinha, String pCodigo, String pNome) {
		return new Linha(pIdLinha, pCodigo, pNome);
	}

}
