package br.com.lo.dimed.poa.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.lo.dimed.poa.entities.Cliente;
import br.com.lo.dimed.poa.entities.ClienteLinha;

public class ClienteDTO {

	@JsonProperty
	public Long id;
	@JsonProperty
	public String nome;
	@JsonProperty
	public String cpf;
	@JsonProperty
	public List<ClienteLinha> linhas;

	public ClienteDTO() {
	}

	public ClienteDTO(Long pId, String pNome, String pCpf, List<ClienteLinha> pLinhas) {
		this.id = pId;
		this.nome = pNome;
		this.cpf = pCpf;
		this.linhas = pLinhas;
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<ClienteLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<ClienteLinha> linhas) {
		this.linhas = linhas;
	}

	public Cliente gerarCliente(String pNome, String pCpf) {
		return new Cliente(pNome, pCpf);
	}
}
