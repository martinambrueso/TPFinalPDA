package com.example.jpa.repository;

import com.example.jpa.model.Alquiler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    Page<Alquiler> findByLectorId(Long lectorId, Pageable pageable);
    Optional<Alquiler> findByIdAndLectorId(Long id, Long lectorId);
    @Query("SELECT ali.titulo, ali.idioma, ale.nombres, ale.apellidos, a.fechaEntrada FROM Alquiler a JOIN a.libro as ali JOIN a.lector as ale WHERE lector_id=:lectorId")
    List<?> getJoinInfo(long lectorId);
}
