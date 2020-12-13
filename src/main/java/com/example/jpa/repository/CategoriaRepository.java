package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
