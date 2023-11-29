package br.org.fundatec.pokeapi.model.dto.converter;

import br.org.fundatec.pokeapi.model.Pokemon;
import br.org.fundatec.pokeapi.model.dto.PokemonRequestDTO;

public class PokemonConverter {

    public static Pokemon converterParaEntidade(PokemonRequestDTO pokemonRequestDTO) {
        Pokemon pokemonEntity = new Pokemon();
        pokemonEntity.setNome(pokemonRequestDTO.getNome());
        pokemonEntity.setTipo(pokemonRequestDTO.getTipo());
        pokemonEntity.setAltura(pokemonRequestDTO.getAltura());
        pokemonEntity.setPeso(pokemonRequestDTO.getPeso());
        pokemonEntity.setLevel(pokemonRequestDTO.getLevel());

        return pokemonEntity;
    }

}
