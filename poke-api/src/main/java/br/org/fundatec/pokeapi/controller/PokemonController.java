package br.org.fundatec.pokeapi.controller;

import br.org.fundatec.pokeapi.model.Pokemon;
import br.org.fundatec.pokeapi.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pokemons")
public class PokemonController {
    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> buscarTodosPokemons() {
        return ResponseEntity.ok(this.pokemonService.buscarTodos());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Pokemon> buscarPokemonPorNome(@PathVariable("nome") String nome) {
        Pokemon PokemonBuscado = this.pokemonService.buscarPokemonPorNome(nome);
        return ResponseEntity.ok(PokemonBuscado);
    }

    @PostMapping()
    public ResponseEntity<Void> adicionar(@RequestBody Pokemon pokemon) {
        pokemonService.adicionar(pokemon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> removerPorId(@PathVariable("id") Long id) {
        pokemonService.removerPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
