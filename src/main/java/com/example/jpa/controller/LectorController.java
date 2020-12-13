package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Lector;
import com.example.jpa.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1")

@RestController
public class LectorController {

    @Autowired
    private LectorRepository lectorRepository;

    @GetMapping("/lectores")
    public Page<Lector> getAllLectores(Pageable pageable) {
        return lectorRepository.findAll(pageable);
    }

    @PostMapping("/lectores")
    public Lector createLector(@Valid @RequestBody Lector lector) {
        return lectorRepository.save(lector);
    }

    @PutMapping("/lectores/{lectorId}")
    public Lector updateLector(@PathVariable Long lectorId, @Valid @RequestBody Lector lectorRequest) {
        return lectorRepository.findById(lectorId).map(lector -> {
        	lector.setApellidos(lectorRequest.getApellidos());
        	lector.setNombres(lectorRequest.getNombres());
        	lector.setCp(lectorRequest.getCp());
        	lector.setDireccion(lectorRequest.getDireccion());
        	lector.setDni(lectorRequest.getDni());
        	lector.setObservaciones(lectorRequest.getObservaciones());
            return lectorRepository.save(lector);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + lectorId + " not found"));
    }
    
    @DeleteMapping("/lectores/{lectorId}")
    public ResponseEntity<?> deleteLector(@PathVariable Long lectorId) {
        return lectorRepository.findById(lectorId).map(lector -> {
        	lectorRepository.delete(lector);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("LectorId " + lectorId + " not found"));
    }

}
