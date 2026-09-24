package com.trads.market_intelligence.service;

import com.trads.market_intelligence.dto.EstadoIbgeDto;
import com.trads.market_intelligence.dto.MunicipioIbgeDto;
import com.trads.market_intelligence.model.Estado;
import com.trads.market_intelligence.model.Municipio;
import com.trads.market_intelligence.repository.EstadoRepository;
import com.trads.market_intelligence.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class IbgeService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sincronizarDadosIbge() {
        // 1. Buscar Estados da API do IBGE
        String urlEstados = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
        EstadoIbgeDto[] estadosDto = restTemplate.getForObject(urlEstados, EstadoIbgeDto[].class);

        if (estadosDto != null) {
            for (EstadoIbgeDto dto : estadosDto) {
                Estado estado = new Estado();
                estado.setId(dto.getId());
                estado.setSigla(dto.getSigla());
                estado.setNome(dto.getNome());
                estado.setRegiao(dto.getRegiao() != null ? dto.getRegiao().getNome() : "N/A");

                estadoRepository.save(estado);

                // 2. Buscar Municípios de cada Estado
                sincronizarMunicipiosPorEstado(estado);
            }
        }
    }

    private void sincronizarMunicipiosPorEstado(Estado estado) {
        String urlMunicipios = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + estado.getSigla() + "/municipios";
        MunicipioIbgeDto[] municipiosDto = restTemplate.getForObject(urlMunicipios, MunicipioIbgeDto[].class);

        if (municipiosDto != null) {
            for (MunicipioIbgeDto dto : municipiosDto) {
                Municipio municipio = new Municipio();
                municipio.setCodigoIbge(dto.getId());
                municipio.setNome(dto.getNome());
                municipio.setEstado(estado);

                // Métrica fictícia/estimada inicial para classificação de mercado
                municipio.setPopulacao(50000L);
                municipio.setPibPerCapita(35000.0);
                municipio.setPotencialMercado("ALTO");

                municipioRepository.save(municipio);
            }
        }
    }
}