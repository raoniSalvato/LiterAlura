package com.projeto.LiterAlura.model;

import com.projeto.LiterAlura.model.dto.DadosLivro;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String idioma;

    @Column
    private int downloadCount;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Livro() {
    }

    public Livro(DadosLivro dadosLivro, Autor autor) {
        this.titulo = dadosLivro.titulo();
        this.idioma = String.join(", ", dadosLivro.idiomas());
        this.downloadCount = dadosLivro.numeroDownloads();
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "------ LIVRO -----" + "\n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + autor.getNome() + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de downloads: " + downloadCount + "\n" +
                "------------------\n";
    }
}
