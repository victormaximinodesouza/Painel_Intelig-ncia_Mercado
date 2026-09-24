package com.trads.market_intelligence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

    @Id
    private Long id; // ID do IBGE (ex: 25 para Paraíba)

    private String sigla;
    private String nome;
    private String regiao;
}