package com.projeto.LiterAlura.service;


import com.projeto.LiterAlura.model.Autor;
import com.projeto.LiterAlura.model.dto.DadosAutor;
import com.projeto.LiterAlura.model.dto.DadosLivro;
import com.projeto.LiterAlura.model.Livro;
import com.projeto.LiterAlura.repository.AutorRepository;
import com.projeto.LiterAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private static final int ANO_DESCONHECIDO = 0;

    public Livro salvarLivro(DadosLivro dadosLivro) {

        Optional<DadosAutor> dadosPrimeiroAutor = dadosLivro.autores().stream().findFirst();

        String nomeAutor = dadosPrimeiroAutor.map(DadosAutor::nomeAutor).orElse("Autor desconhecido");
        Integer anoNascimento = dadosPrimeiroAutor.map(DadosAutor::anoNascimento).orElse(null);
        Integer anoMorte = dadosPrimeiroAutor.map(DadosAutor::anoMorte).orElse(null);

        Autor autor = autorRepository.findByNome(nomeAutor).orElseGet(() -> {
            Autor novoAutor = new Autor(nomeAutor,
                    Optional.ofNullable(anoNascimento).orElse(ANO_DESCONHECIDO),
                    Optional.ofNullable(anoMorte).orElse(ANO_DESCONHECIDO));
            return autorRepository.save(novoAutor);
        });

        boolean precisaAtualizar = false;

        if (anoNascimento != null && autor.getAnoNascimento() == ANO_DESCONHECIDO) {
            autor.setAnoNascimento(anoNascimento);
            precisaAtualizar = true;
        }
        if (anoMorte != null && autor.getAnoMorte() == ANO_DESCONHECIDO) {
            autor.setAnoMorte(anoMorte);
            precisaAtualizar = true;
        }
        if (precisaAtualizar) {
            autorRepository.save(autor);
        }

        Livro livro = new Livro(dadosLivro, autor);
        return livroRepository.save(livro);
    }
}
