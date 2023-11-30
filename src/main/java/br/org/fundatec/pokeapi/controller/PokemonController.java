package br.org.fundatec.pokeapi.controller;

import br.org.fundatec.pokeapi.integration.response.PokemonResponse;
import br.org.fundatec.pokeapi.integration.service.PokemonIntegrationService;
import br.org.fundatec.pokeapi.model.Pokemon;
import br.org.fundatec.pokeapi.model.dto.PokemonRequestDTO;
import br.org.fundatec.pokeapi.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pokemons")
public class PokemonController {
    private PokemonService pokemonService;
    private PokemonIntegrationService pokemonIntegrationService;

    public PokemonController(PokemonService pokemonService, PokemonIntegrationService pokemonIntegrationService) {
        this.pokemonService = pokemonService;
        this.pokemonIntegrationService = pokemonIntegrationService;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> buscarTodosPokemons() {
        return ResponseEntity.ok(this.pokemonService.buscarTodos());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Pokemon> buscarPokemonPorNome(@PathVariable("nome") String nome) {
        Pokemon PokemonBuscado = this.pokemonService.buscarPokemonPorNome(nome);
        return ResponseEntity.ok(PokemonBuscado);
    }

    @PostMapping()
    public ResponseEntity<Void> adicionarPokemon(@RequestBody PokemonRequestDTO pokemonRequestDTO) {
        pokemonService.adicionar(pokemonRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> removerPokemonPorId(@PathVariable("id") Long id) {
        pokemonService.removerPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Pokemon> removerPorNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(this.pokemonService.removerPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> alterarPokemonPorId(@PathVariable("id") Long id,
                                                        @RequestBody Pokemon pokemon) {
        Pokemon pokemonAlterado = this.pokemonService.alterarPokemonPorId(id, pokemon);
        return ResponseEntity.ok(pokemonAlterado);
    }

    @GetMapping("/api-externa/{nome}")
    public ResponseEntity<PokemonResponse> buscarPokemonServicoExterno(@PathVariable("nome") String nome) {
        PokemonResponse pokemonBuscadoServicoExterno = this.pokemonIntegrationService.buscarPokemonNoServicoExternoPeloNome(nome);
        return ResponseEntity.ok(pokemonBuscadoServicoExterno);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> buscarTodosPokemonEQuantidade() {
        return ResponseEntity.ok(this.pokemonService.buscarTodosPokemonEQuantidade());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity <List<Pokemon>> buscarPokemonPorTipo(@PathVariable("tipo") String tipo) {
        return ResponseEntity.ok(this.pokemonService.buscarPokemonPorTipo(tipo));
    }
}
