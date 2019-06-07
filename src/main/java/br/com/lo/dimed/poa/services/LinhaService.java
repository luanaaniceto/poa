package br.com.lo.dimed.poa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.lo.dimed.poa.dto.LinhaDTO;
import br.com.lo.dimed.poa.entities.Linha;
import br.com.lo.dimed.poa.repositories.LinhaRepository;
import br.com.lo.dimed.poa.util.Util;

@Service
public class LinhaService {

	@Autowired
	private LinhaRepository linhaRepository;

	@Autowired
	private ItinerarioService itinerarioService;
	
	@Autowired
	private PosicionamentoService posicionamentoService;
	
	public List<Linha> localizarLinhas() {
		return linhaRepository.findAll();
	}

	public List<Linha> localizarLinhaNome(String nome) {
		return linhaRepository.findByNomeIgnoreCase(nome);
	}

	@Transactional(readOnly = false)
	public List<Linha> carregarLinha() {
		List<LinhaDTO> listaLinhas = localizaLinhasApi();

		try {
			for (LinhaDTO l : listaLinhas) {
				LinhaDTO linhaDTO = new LinhaDTO();
				linhaRepository.save(linhaDTO.gerarLinhas(l.getId(), l.getNome(), l.getCodigo()));
				itinerarioService.salvarItinerario(l.getId());
				posicionamentoService.salvarPosicionamento(l.getId());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return linhaRepository.findAll();
	}

	public List<LinhaDTO> localizaLinhasApi() {
		List<LinhaDTO> listaLinhasDTO = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(Util.getMessageConverterHtml());
			ResponseEntity<List<LinhaDTO>> rateResponse = restTemplate.exchange(
					"http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<LinhaDTO>>() {
					});
			listaLinhasDTO = rateResponse.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listaLinhasDTO;
	}

	public Linha buscarLinhaPorIdLinha(Long idLinha) {
		return linhaRepository.findByIdLinha(idLinha);
	}
}
