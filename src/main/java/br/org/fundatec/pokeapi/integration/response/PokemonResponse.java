package br.org.fundatec.pokeapi.integration.response;

import lombok.Data;

@Data
public class PokemonResponse {
    private int id;
    private String name;
    private int height;
    private int weight;

}
