package br.org.fundatec.pokeapi.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PokemonRequestDTO {

    private String nome;
    private String tipo;
    private Double altura;
    private Double peso;
    private Integer level;

}
