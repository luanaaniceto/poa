package br.com.lo.dimed.poa.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lo.dimed.poa.dto.ClienteDTO;
import br.com.lo.dimed.poa.dto.ItinerarioDTO;
import br.com.lo.dimed.poa.entities.Linha;
import br.com.lo.dimed.poa.services.ClienteService;
import br.com.lo.dimed.poa.services.ItinerarioService;
import br.com.lo.dimed.poa.services.LinhaService;
import br.com.lo.dimed.poa.services.PosicionamentoService;

@RestController
@RequestMapping("/poa")
public class AppController {

	@Autowired
	private LinhaService linhaService;
	
	@Autowired
	private ItinerarioService itinerarioService;
	
	@Autowired
	private PosicionamentoService posicionamentoService;
	
	@Autowired
	private ClienteService clienteService;
	
	/**
	 * Carregar Linhas, Itinerários e Posicionamento no sistema.
	 */
	@GetMapping("/carregar/linhas")
	public ResponseEntity<List<Linha>> carregarLinha() {
		return ResponseEntity.ok(linhaService.carregarLinha());
	}
	
	/**
	 * Listar as linhas de ônibus - http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o 
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/linhas")
	public ResponseEntity<List<Linha>> localizarLinhas() throws IOException {
		return ResponseEntity.ok(linhaService.localizarLinhas());
	}
	
	/**
	 * Filtrando as linhas de ônibus por nome. 
	 * @param nome
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/linha/{nome}")
	public ResponseEntity<List<Linha>> localizarLinhaNome(@PathVariable String nome) throws IOException {
		return ResponseEntity.ok(linhaService.localizarLinhaNome(nome));
	}
	
	/**
	 *  Listar itinerário de uma determinada unidade de transporte - http://www.poatransporte.com.br/php/facades/process.php?a=il&p=5566 
	 * @param id
	 * @return
	 */
	@GetMapping("/itinerario/{id}")
	public ResponseEntity<List<ItinerarioDTO>> localizarItinerarioApi(@PathVariable Long id){
		return ResponseEntity.ok(itinerarioService.buscarItinerarioPosicionamentoLinhaId(id));
	}
	
	/**
	 * Criar um CRUD de cliente, onde consiga cadastrar suas linhas de Ônibus. 
	 * @param input
	 * @return
	 */
	@PostMapping("/cliente")
    public ResponseEntity<ClienteDTO> salvarClienteLinha(@RequestBody String input) {
        return ResponseEntity.ok(clienteService.salvar(input));
    }
	
	/**
	 * Criar uma operação que receba uma latitude, longitude e raio, deverá retornar quais unidades de transporte se encontraram nessa faixa de busca. 
	 * @param latitude
	 * @param longitude
	 * @param raio
	 * @return
	 */
	@GetMapping("/linhas/distancia/{latitude}/{longitude}/{raio}")
	public ResponseEntity<List<Linha>> localizarLinhasLatLongRaio(@PathVariable Double latitude, @PathVariable Double longitude, @PathVariable Double raio){
		return ResponseEntity.ok(posicionamentoService.buscarLinhasLatitudeLongitudeRaio(latitude, longitude, raio));
	}
	
	/**
	 * Criar uma operação que faça o filtro de raio de uma determinada unidade de transporte trazendo somente as latitudes que se encaixam nessa busca. 
	 * Exemplo na request conterá a unidade de transporte a latitude e longitude e o raio que desejo filtrar. 
	 * @param latitude
	 * @param longitude
	 * @param raio
	 * @param idLinha
	 * @return
	 */
	@GetMapping("/linhas/latitude/{latitude}/{longitude}/{raio}/{idLinha}")
	public ResponseEntity<Map<Integer, Double>> getLatitudePorLinha(@PathVariable Double latitude, @PathVariable Double longitude, @PathVariable Double raio, @PathVariable Long idLinha){
		return ResponseEntity.ok(posicionamentoService.getLatitudePorLinha(latitude, longitude, raio, idLinha));
	}
	
}
