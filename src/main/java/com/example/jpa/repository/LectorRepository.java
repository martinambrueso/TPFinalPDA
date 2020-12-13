package com.example.jpa.repository;

import com.example.jpa.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

}
