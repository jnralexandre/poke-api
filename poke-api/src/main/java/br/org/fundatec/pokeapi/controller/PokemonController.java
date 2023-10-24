package br.org.fundatec.pokeapi.controller;

import br.org.fundatec.pokeapi.model.Pokemon;
import br.org.fundatec.pokeapi.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pokemons")
public class PokemonController {
    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> BuscarTodosPokemons() {
        return ResponseEntity.ok(this.pokemonService.buscarTodos());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Pokemon> BuscarPokemonPorNome(@PathVariable("nome") String nome) {
        Pokemon PokemonBuscado = this.pokemonService.buscarPokemonPorNome(nome);
        return ResponseEntity.ok(PokemonBuscado);
    }
}
