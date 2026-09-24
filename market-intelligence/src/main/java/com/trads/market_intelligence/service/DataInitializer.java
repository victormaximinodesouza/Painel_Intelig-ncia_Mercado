package com.trads.market_intelligence.service;

import com.trads.market_intelligence.repository.EstadoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final IbgeService ibgeService;
    private final EstadoRepository estadoRepository;

    public DataInitializer(IbgeService ibgeService, EstadoRepository estadoRepository) {
        this.ibgeService = ibgeService;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Se o banco de dados estiver vazio, faz a carga inicial do IBGE automaticamente
        if (estadoRepository.count() == 0) {
            System.out.println("Iniciando carga automática dos dados do IBGE...");
            ibgeService.sincronizarDadosIbge();
            System.out.println("Carga do IBGE concluída!");
        }
    }
}