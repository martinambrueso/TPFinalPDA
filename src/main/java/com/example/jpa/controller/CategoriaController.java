package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Categoria;
import com.example.jpa.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1")

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public Page<Categoria> getAllEditoriales(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    @PostMapping("/categorias")
    public Categoria createEditorial(@Valid @RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @PutMapping("/categorias/{categoriaId}")
    public Categoria updateEditorial(@PathVariable Long categoriaId, @Valid @RequestBody Categoria categoriaRequest) {
        return categoriaRepository.findById(categoriaId).map(categoria -> {
        	categoria.setNombre(categoriaRequest.getNombre());    
            return categoriaRepository.save(categoria);
        }).orElseThrow(() -> new ResourceNotFoundException("CategoriaId " + categoriaId + " not found"));
    }
    
    @DeleteMapping("/categorias/{categoriaId}")
    public ResponseEntity<?> deleteEditorial(@PathVariable Long categoriaId) {
        return categoriaRepository.findById(categoriaId).map(categoria -> {
        	categoriaRepository.delete(categoria);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CategoriaId " + categoriaId + " not found"));
    }

}
