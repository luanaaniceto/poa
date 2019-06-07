package br.com.lo.dimed.poa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lo.dimed.poa.entities.Cliente;
import br.com.lo.dimed.poa.entities.ClienteLinha;
import br.com.lo.dimed.poa.entities.Linha;
import br.com.lo.dimed.poa.repositories.ClienteLinhaRepository;

@Service
public class ClienteLinhaService {

	@Autowired
	ClienteLinhaRepository clienteLinhaRepository;
	
	@Transactional(readOnly = false)
	public ClienteLinha salvar(Cliente cliente, Linha linha) {
		return clienteLinhaRepository.save(new ClienteLinha(cliente.getId(), linha.getIdLinha()));
	}

	public List<ClienteLinha> localizarLinhasCliente(Cliente cliente) {
		return clienteLinhaRepository.findByIdCliente(cliente.getId());
	}
}
