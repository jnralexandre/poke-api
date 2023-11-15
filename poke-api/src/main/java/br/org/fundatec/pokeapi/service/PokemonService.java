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

    public void adicionar(Pokemon pokemon) {
        pokemonRepository.save(pokemon);
    }

    public Pokemon removerPorId(Long id) {
        Pokemon pokemonParaRemover = pokemonRepository.findById(id).get();
        pokemonRepository.deleteById(id);
        return pokemonParaRemover;
    }

    public Pokemon removerPorNome(String nome) {
        Pokemon pokemonParaRemover = pokemonRepository.findByNome(nome);
        pokemonRepository.delete(pokemonParaRemover);
        return pokemonParaRemover;
    }

    public Pokemon alterarPokemonPorId(Long id, Pokemon pokemon) {
        Pokemon pokemonParaAlterar = pokemonRepository.findById(id).get();
        pokemonParaAlterar.setNome(pokemon.getNome());
        pokemonParaAlterar.setTipo(pokemon.getTipo());
        pokemonRepository.save(pokemonParaAlterar);
        return pokemonParaAlterar;
    }

}
