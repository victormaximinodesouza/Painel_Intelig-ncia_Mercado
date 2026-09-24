package com.trads.market_intelligence.repository;

import com.trads.market_intelligence.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    List<Municipio> findByEstadoSigla(String sigla);
    List<Municipio> findByPotencialMercado(String potencialMercado);
}