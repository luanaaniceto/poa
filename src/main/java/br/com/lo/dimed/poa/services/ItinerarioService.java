package br.com.lo.dimed.poa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.lo.dimed.poa.dto.ItinerarioDTO;
import br.com.lo.dimed.poa.entities.Itinerario;
import br.com.lo.dimed.poa.repositories.ItinerarioRepository;
import br.com.lo.dimed.poa.util.Util;

@Service
public class ItinerarioService {

	@Autowired
	ItinerarioRepository itinerarioRepository;

	@Autowired
	LinhaService linhaService;

	@Autowired
	PosicionamentoService posicionamentoService;

	@Transactional(readOnly = false)
	public void salvarItinerario(Long id) {
		try {
			List<ItinerarioDTO> itinerario = buscarItinerarioLinhaId(id);

			for (ItinerarioDTO i : itinerario) {
				ItinerarioDTO iti = new ItinerarioDTO();
				itinerarioRepository.save(iti.gerarItinerario(i.getIdlinha(), i.getNome(), i.getCodigo()));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private List<ItinerarioDTO> buscarItinerarioLinhaId(Long id) {
		List<ItinerarioDTO> itinerario = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(Util.getMessageConverterHtml());
			String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=" + id;

			ItinerarioDTO itinerarioObj = restTemplate.getForObject(url, ItinerarioDTO.class);
			itinerario = new ArrayList<ItinerarioDTO>();
			itinerario.add(itinerarioObj);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return itinerario;
	}

	public List<ItinerarioDTO> buscarItinerarioPosicionamentoLinhaId(Long id) {
		List<ItinerarioDTO> itinerario = null;
		itinerario = this.buscarItinerarioLinhaId(id);

		for (ItinerarioDTO dto : itinerario) {
			dto.setPosicionamentoDTO(posicionamentoService.buscarPosicionamentoLinhaId(id));
		}
		return itinerario;
	}

	public Itinerario buscarItinerarioPorId(Long id) {
		return itinerarioRepository.getItinerario(id);
	}

	public Itinerario buscarItinerarioPorIdLinha(Long idLinha) {
		return itinerarioRepository.findByidlinha(idLinha);
	}

}
