package com.trads.market_intelligence.dto;

import lombok.Data;

@Data
public class EstadoIbgeDto {
    private Long id;
    private String sigla;
    private String nome;
    private RegiaoDto regiao;

    @Data
    public static class RegiaoDto {
        private String nome;
    }
}