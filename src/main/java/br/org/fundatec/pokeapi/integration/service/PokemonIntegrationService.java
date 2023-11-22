package br.org.fundatec.pokeapi.integration.service;

import br.org.fundatec.pokeapi.integration.response.PokemonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Service
public class PokemonIntegrationService {

    private final RestTemplate restTemplate;
    @Value("${pokemon-external-api}")
    private String uri;

    public PokemonIntegrationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PokemonResponse buscarPokemonNoServicoExternoPeloNome(String nome) {
        String urlCompleta = this.uri + "/" + nome;
        PokemonResponse pokemonExterno = this.restTemplate.getForObject(urlCompleta, PokemonResponse.class);

        String nomePokemonAPIExterna = pokemonExterno.getName();
        String nomeComInicialMaiuscula =
                nomePokemonAPIExterna.substring(0, 1).toUpperCase() +
                        nomePokemonAPIExterna.substring(1);
        pokemonExterno.setName(nomeComInicialMaiuscula);

        return pokemonExterno;
    }
}
