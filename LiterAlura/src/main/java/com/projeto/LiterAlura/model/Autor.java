package com.projeto.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int anoNascimento;

    @Column
    private int anoMorte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {
    }

    public Autor(String nome, int anoNascimento, int anoMorte) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoMorte = anoMorte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(int anoMorte) {
        this.anoMorte = anoMorte;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        String infoLivros = livros.isEmpty() ? "Nenhum livro cadastrado"
                : livros.stream().map(Livro::getTitulo).findFirst().orElse("Sem título");

        if (anoMorte > 0) {
            return "------ AUTOR ------\n" +
                    "Nome                : " + nome + "\n" +
                    "Ano de nascimento   : " + anoNascimento + "\n" +
                    "Ano de falecimento  : " + anoMorte + "\n" +
                    "Livro cadastrado    : " + infoLivros + "\n" +
                    "--------------------\n";
        } else {
            return "------ AUTOR ------\n" +
                    "Nome                : " + nome + "\n" +
                    "Ano de nascimento   : " + anoNascimento + "\n" +
                    "Autor ainda vivo\n" +
                    "Livro cadastrado     : " + infoLivros + "\n" +
                    "--------------------\n";
        }
    }
}
