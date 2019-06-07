package br.com.lo.dimed.poa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente_linha")
public class ClienteLinha {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	protected Long id;

	@Column(name = "idCliente")
	protected Long idCliente;

	@Column(name = "idLinha")
	protected Long idLinha;

	public ClienteLinha() {
	}

	public ClienteLinha(Long pIdCliente, Long pIdLinha) {
		this.idCliente = pIdCliente;
		this.idLinha = pIdLinha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(Long idLinha) {
		this.idLinha = idLinha;
	}

}
