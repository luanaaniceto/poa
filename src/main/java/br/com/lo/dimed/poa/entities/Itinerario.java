package br.com.lo.dimed.poa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itinerario")
public class Itinerario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	protected Long id;

	@Column(name = "idlinha")
	protected Long idlinha;

	@Column(name = "nome")
	protected String nome;

	@Column(name = "codigo")
	protected String codigo;

	public Itinerario() {
	}

	public Itinerario(Long pIdlinha, String pNome, String pCodigo) {
		this.idlinha = pIdlinha;
		this.nome = pNome;
		this.codigo = pCodigo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdlinha() {
		return idlinha;
	}

	public void setIdlinha(Long idLinha) {
		this.idlinha = idLinha;
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

}
