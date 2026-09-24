package com.trads.market_intelligence.dto;

import lombok.Data;

@Data
public class MunicipioIbgeDto {
    private Long id;
    private String nome;
    private EstadoIbgeDto microrregiao;
}