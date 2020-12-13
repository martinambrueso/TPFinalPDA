package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Autor;
import com.example.jpa.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1")

@RestController
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping("/autores")
    public Page<Autor> getAllEditoriales(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    @PostMapping("/autores")
    public Autor createEditorial(@Valid @RequestBody Autor autor) {
        return autorRepository.save(autor);
    }

    @PutMapping("/autores/{autorId}")
    public Autor updateEditorial(@PathVariable Long autorId, @Valid @RequestBody Autor autorRequest) {
        return autorRepository.findById(autorId).map(autor -> {
        	autor.setApellido(autorRequest.getApellido());
        	autor.setNombre(autorRequest.getNombre()); 
        	autor.setEmail(autorRequest.getEmail()); 
            return autorRepository.save(autor);
        }).orElseThrow(() -> new ResourceNotFoundException("AutorId" + autorId + " not found"));
    }
    
    @DeleteMapping("/autores/{categoriaId}")
    public ResponseEntity<?> deleteEditorial(@PathVariable Long autorId) {
        return autorRepository.findById(autorId).map(categoria -> {
        	autorRepository.delete(categoria);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("AutorId " + autorId + " not found"));
    }

}
