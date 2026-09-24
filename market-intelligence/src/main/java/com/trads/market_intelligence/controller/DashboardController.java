package com.trads.market_intelligence.controller;

import com.trads.market_intelligence.model.Estado;
import com.trads.market_intelligence.model.Municipio;
import com.trads.market_intelligence.repository.EstadoRepository;
import com.trads.market_intelligence.repository.MunicipioRepository;
import com.trads.market_intelligence.service.IbgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*") // Permite requisições do Frontend (React)
public class DashboardController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private IbgeService ibgeService;

    @GetMapping("/estados")
    public List<Estado> listarEstados() {
        return estadoRepository.findAll();
    }

    @GetMapping("/municipios")
    public List<Municipio> listarMunicipios(@RequestParam(required = false) String estadoSigla) {
        if (estadoSigla != null && !estadoSigla.isEmpty()) {
            return municipioRepository.findByEstadoSigla(estadoSigla);
        }
        return municipioRepository.findAll();
    }

    @PostMapping("/sincronizar")
    public ResponseEntity<String> sincronizar() {
        ibgeService.sincronizarDadosIbge();
        return ResponseEntity.ok("Dados do IBGE sincronizados com sucesso!");
    }
}