package com.trads.market_intelligence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "municipios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Municipio {

    @Id
    private Long codigoIbge; // Código IBGE de 7 dígitos

    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    private Long populacao;
    private Double pibPerCapita;
    private Double rendaMedia;
    private String potencialMercado; // Ex: 'ALTO', 'MEDIO', 'BAIXO'
}