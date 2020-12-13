package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Alquiler;
import com.example.jpa.repository.AlquilerRepository;
import com.example.jpa.repository.LectorRepository;
import com.example.jpa.repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1")

@RestController
public class AlquilerController {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private LectorRepository lectorRepository;
    
    @Autowired
    private LibroRepository libroRepository;

    @GetMapping("/lectores/{lectorId}/alquileres")
    public Page<Alquiler> getAllAlquileresByLector(@PathVariable (value = "lectorId") Long lectorId,
                                                Pageable pageable) {
        return alquilerRepository.findByLectorId(lectorId, pageable);
    }

    @PostMapping("/lectores/{lectorId}/alquileres")
    public Alquiler createComment(@RequestParam long idLibro, @PathVariable (value = "lectorId") Long lectorId,
                                 @Valid @RequestBody Alquiler alquilerRequest) {
    	
    	libroRepository.findById(idLibro).map(libro -> {
        	alquilerRequest.setLibro(libro);
            return alquilerRequest;
        }).orElseThrow(() -> new ResourceNotFoundException("LibroId " + idLibro + " not found"));
    	
    	lectorRepository.findById(lectorId).map(lector -> {
        	alquilerRequest.setLector(lector);
            return alquilerRequest;
        }).orElseThrow(() -> new ResourceNotFoundException("LectorId " + lectorId + " not found"));
    	
    	return alquilerRepository.save(alquilerRequest);
    }

    @PutMapping("/lectores/{lectorId}/alquileres/{alquilerId}")
    public Alquiler updateAlquiler(@PathVariable (value = "lectorId") Long lectorId,
                                 @PathVariable (value = "alquilerId") Long alquilerId,
                                 @Valid @RequestBody Alquiler alquilerRequest) {
        if(!lectorRepository.existsById(lectorId)) {
            throw new ResourceNotFoundException("LectorId " + lectorId + " not found");
        }

        return alquilerRepository.findById(alquilerId).map(alquiler -> {
        	alquiler.setFechaEntrada(alquilerRequest.getFechaEntrada());
        	alquiler.setFechaSalida(alquilerRequest.getFechaSalida());
            return alquilerRepository.save(alquiler);
        }).orElseThrow(() -> new ResourceNotFoundException("AlquilerId " + alquilerId + "not found"));
    }

    @DeleteMapping("/lectores/{lectorId}/alquileres/{alquilerId}")
    public ResponseEntity<?> deleteAlquiler(@PathVariable (value = "lectorId") Long lectorId,
                              @PathVariable (value = "alquilerId") Long alquilerId) {
        return alquilerRepository.findByIdAndLectorId(alquilerId, lectorId).map(alquiler -> {
        	alquilerRepository.delete(alquiler);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Alquier not found with id " + alquilerId + " and lectorId " + lectorId));
    }
    
}
