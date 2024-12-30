package com.projeto.LiterAlura.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("name") String nomeAutor,
                         @JsonAlias("birth_year") int anoNascimento,
                         @JsonAlias("death_year") int anoMorte) {

}
