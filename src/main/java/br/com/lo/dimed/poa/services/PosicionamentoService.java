package br.com.lo.dimed.poa.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lo.dimed.poa.dto.PosicionamentoDTO;
import br.com.lo.dimed.poa.entities.Itinerario;
import br.com.lo.dimed.poa.entities.Linha;
import br.com.lo.dimed.poa.repositories.PosicionamentoRepository;

@Service
public class PosicionamentoService {

	@Autowired
	PosicionamentoRepository posicionamentoRepository;

	@Autowired
	ItinerarioService itinerarioService;

	@Autowired
	LinhaService linhaService;

	@Transactional(readOnly = false)
	public void salvarPosicionamento(Long id) {
		try {
			List<PosicionamentoDTO> posicionamento = buscarPosicionamentoLinhaId(id);
			Long idItinerario = itinerarioService.buscarItinerarioPorIdLinha(id).getId();

			for (PosicionamentoDTO p : posicionamento) {
				PosicionamentoDTO posicao = new PosicionamentoDTO();
				posicionamentoRepository.save(posicao.gerarPosicionamento(idItinerario, p.getLat(), p.getLng()));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public List<Linha> buscarLinhasLatitudeLongitudeRaio(Double latitude, Double longitude, Double raio) {
		List<Linha> linhas = new ArrayList<Linha>();
		ArrayList<?> posicionamento = posicionamentoRepository.getLatitudeLongitudeRaio(latitude, longitude, raio);
		for (int i = 0; i < posicionamento.size(); i++) {
			Object[] obj = (Object[]) posicionamento.get(i);
			Long id = Long.valueOf(obj[0].toString());
			Itinerario itinerario = itinerarioService.buscarItinerarioPorId(id);
			linhas.add(linhaService.buscarLinhaPorIdLinha(itinerario.getIdlinha()));
		}
		return linhas;
	}

	public Map<Integer, Double> getLatitudePorLinha(Double latitude, Double longitude, Double raio, Long idLinha) {
		ArrayList<?> posicionamento = posicionamentoRepository.getLatitudeLongitudeRaio(latitude, longitude, raio);
		Map<Integer, Double> map = new LinkedHashMap<Integer, Double>();
		for (int i = 0; i < posicionamento.size(); i++) {
			Object[] obj = (Object[]) posicionamento.get(i);
			Double lat = Double.valueOf(obj[1].toString());
			map.put(i, lat);
		}

		return map;
	}

	public List<PosicionamentoDTO> buscarPosicionamentoLinhaId(Long idLinha) {
		List<PosicionamentoDTO> posicionamento = null;
		try {
			String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=" + idLinha;
			Document document = Jsoup.connect(url).get();
			Elements element = document.select("body");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode json;
			json = mapper.readTree(element.get(0).text());
			posicionamento = new ArrayList<PosicionamentoDTO>();
			PosicionamentoDTO posicaoObj = new PosicionamentoDTO();
			for (JsonNode j : json) {
				if (!j.findValuesAsText("lat").isEmpty() && !j.findValuesAsText("lng").isEmpty()) {
					posicaoObj.setLat(Double.parseDouble(j.findValuesAsText("lat").get(0)));
					posicaoObj.setLng(Double.parseDouble(j.findValuesAsText("lng").get(0)));
					posicionamento.add(posicaoObj);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return posicionamento;
	}
}
