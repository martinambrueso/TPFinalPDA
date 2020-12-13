package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
