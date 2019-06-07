package br.com.lo.dimed.poa.services;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lo.dimed.poa.dto.ClienteDTO;
import br.com.lo.dimed.poa.entities.Cliente;
import br.com.lo.dimed.poa.entities.ClienteLinha;
import br.com.lo.dimed.poa.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ClienteLinhaService clienteLinhaService;

	@Autowired
	LinhaService linhaService;

	@Transactional(readOnly = false)
	public ClienteDTO salvar(String jSon) {
		JSONObject clienteJson = new JSONObject(jSon);
		Cliente cliente = new Cliente(clienteJson.getString("nome"), clienteJson.getString("cpf"));
		clienteRepository.save(cliente);
		JSONArray arrayLinhas = clienteJson.getJSONArray("linhas");
		this.salvarLinhasCliente(cliente, arrayLinhas);
		return localizarClienteLinhas(cliente);
	}

	@Transactional(readOnly = false)
	private void salvarLinhasCliente(Cliente cliente, JSONArray arrayLinhas) {
		if (arrayLinhas.isEmpty())
			return;
		for (int i = 0; i < arrayLinhas.length(); i++) {
			JSONObject objLinha = (JSONObject) arrayLinhas.get(i);
			clienteLinhaService.salvar(cliente, linhaService.buscarLinhaPorIdLinha(objLinha.getLong("id")));
		}
	}

	private ClienteDTO localizarClienteLinhas(Cliente cliente) {
		List<ClienteLinha> clienteLinha = clienteLinhaService.localizarLinhasCliente(cliente);
		return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), clienteLinha);
	}
}
