# LiterAlura - Sistema de Gerenciamento de Livros e Autores

O **LiterAlura** é uma aplicação Spring Boot que permite realizar buscas, registrar e listar livros e autores. Ele integra dados de uma API externa para obter informações sobre livros e oferece funcionalidades de consulta, cadastro e filtro de livros e autores registrados no banco de dados.

## Funcionalidades

- **Buscar Livro pelo Título**: O usuário pode buscar um livro utilizando seu título. A aplicação faz uma requisição à API externa Gutendex para recuperar as informações do livro e, em seguida, registra essas informações no banco de dados.
  
- **Listar Livros Registrados**: Exibe todos os livros que estão registrados no banco de dados.
  
- **Listar Autores Registrados**: Exibe todos os autores cadastrados no banco de dados.
  
- **Listar Autores Vivos em um Determinado Ano**: O usuário pode consultar quais autores estavam vivos em um ano específico, verificando sua data de nascimento e morte.
  
- **Listar Livros por Idioma**: Permite ao usuário filtrar livros registrados no banco de dados por idioma, incluindo opções como espanhol, inglês, francês e português.

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para o desenvolvimento da aplicação, oferecendo integração fácil com o banco de dados e APIs externas.
  
- **Spring Data JPA**: Utilizado para interagir com o banco de dados, permitindo operações CRUD com as entidades `Livro` e `Autor`.
  
- **API Gutendex**: API externa que fornece dados sobre livros, consumida pela aplicação para realizar buscas de livros por título.
  
- **Jackson**: Biblioteca para processamento de JSON, usada para converter as respostas da API externa em objetos Java.
  
- **Java**: Linguagem de programação utilizada para o desenvolvimento da aplicação.
  
- **H2 Database**: Banco de dados utilizado para persistência dos dados de livros e autores durante o desenvolvimento (pode ser substituído por outro banco de dados em produção).

