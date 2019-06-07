package br.com.lo.dimed.poa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "linha")
public class Linha {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	protected Long id;

	@Column(name = "idLinha")
	protected Long idLinha;

	@Column(name = "codigo")
	protected String codigo;

	@Column(name = "nome")
	protected String nome;

	public Linha() {
	}

	public Linha(Long pIdLinha, String pCodigo, String pNome) {
		this.idLinha = pIdLinha;
		this.codigo = pCodigo;
		this.nome = pNome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(Long idLinha) {
		this.idLinha = idLinha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
