package br.org.fundatec.pokeapi.service;

import br.org.fundatec.pokeapi.model.Pokemon;
import br.org.fundatec.pokeapi.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> buscarTodos() {
        return pokemonRepository.findAll();
    }

    public Pokemon buscarPokemonPorNome(String nome) {
        return pokemonRepository.findByNome(nome);
    }

}
