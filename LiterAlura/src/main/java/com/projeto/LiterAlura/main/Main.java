package com.projeto.LiterAlura.main;

import com.projeto.LiterAlura.model.Autor;
import com.projeto.LiterAlura.repository.AutorRepository;
import com.projeto.LiterAlura.api.ConsumeApi;
import com.projeto.LiterAlura.util.ConvertData;
import com.projeto.LiterAlura.model.dto.DadosLivro;
import com.projeto.LiterAlura.model.Livro;
import com.projeto.LiterAlura.repository.LivroRepository;
import com.projeto.LiterAlura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Main {

    private Scanner scanner = new Scanner(System.in);
    private ConsumeApi consumeApi = new ConsumeApi();
    private ConvertData convertData = new ConvertData();
    private final String LINK = "https://gutendex.com/books?search=";
    private LivroRepository livroRepository;
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private LivroService livroService;
    private AutorRepository autorRepository;
    String idiomas = """
                es - espanhol
                en - inglês
                fr - francês
                pt - português
            """;

    @Autowired
    public Main(ConsumeApi consumeApi, ConvertData convertData, LivroService livroService, LivroRepository livroRepository, AutorRepository autorRepository) {
        this.consumeApi = consumeApi;
        this.convertData = convertData;
        this.livroService = livroService;
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void menu(){
        var opcao = 1;
        while(opcao != 0){

            var menu = """
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                
                0 - Sair
                """;

            System.out.printf(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;

                case 3:
                    listarAutoresRegistrados();
                    break;

                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosIdioma();
                    break;
                case 0:
                    System.out.printf("Saindo...");
                    break;
                default:
                    System.out.printf("Opção invalida");
            }
        }
    }

    private void buscarLivroPeloTitulo() {
        DadosLivro dadosLivro = getDadosLivro();
        livroService.salvarLivro(dadosLivro);
    }

    private DadosLivro getDadosLivro() {
        System.out.printf("Digite o nome do livro para buscar: ");
        var nomeLivro = scanner.nextLine();
        var json = consumeApi.getData(LINK + nomeLivro.replace(" ", "%20"));
        return convertData.getFirstObject(json, "results", DadosLivro.class);
    }

    private void listarLivrosRegistrados() {
        livros = livroRepository.findAll();
        livros.stream().forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores = autorRepository.findAll();
        autores.stream().forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        int ano;
        System.out.printf("Digite o ano que deseja pesquisar: ");
        ano = scanner.nextInt();
        List<Autor> autoresVivos = autorRepository.autoresVivos(ano);
        if(autoresVivos.isEmpty()){
            System.out.printf("Nenhum autor encontrado que estivesse vivo no ano " + ano + "\n");
        }else {
            System.out.printf("Autores vivos no ano " + ano + ":\n");
            autoresVivos.stream().forEach(System.out::println);
        }
    }

    private void listarLivrosIdioma() {
        System.out.printf("Insira o idioma para realizar a busca: \n");
        System.out.printf(idiomas);
        String idiomaEscolhido = scanner.nextLine();
        List<Livro> livroPorIdioma = livroRepository.findByIdioma(idiomaEscolhido);
        if(livroPorIdioma.isEmpty()){
            System.out.printf("Nenhum livro encontrado do idioma " + idiomaEscolhido + "\n");
        }else{
            System.out.printf("Livros do idioma " + idiomaEscolhido + ":\n");
            livroPorIdioma.stream().forEach(System.out::println);
        }
    }

}
