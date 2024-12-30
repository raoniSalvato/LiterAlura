package com.projeto.LiterAlura.repository;

import com.projeto.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long>{

    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anoMorte >= :ano OR a.anoMorte = 0")
    List<Autor> autoresVivos(int ano);




}
