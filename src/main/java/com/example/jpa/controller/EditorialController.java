package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Editorial;
import com.example.jpa.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1")

@RestController
public class EditorialController {

    @Autowired
    private EditorialRepository editorialRepository;

    @GetMapping("/editoriales")
    public Page<Editorial> getAllEditoriales(Pageable pageable) {
        return editorialRepository.findAll(pageable);
    }

    @PostMapping("/editoriales")
    public Editorial createEditorial(@Valid @RequestBody Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @PutMapping("/editoriales/{editorialId}")
    public Editorial updateEditorial(@PathVariable Long editorialId, @Valid @RequestBody Editorial editorialRequest) {
        return editorialRepository.findById(editorialId).map(editorial -> {
        	editorial.setNombre(editorialRequest.getNombre());    
            return editorialRepository.save(editorial);
        }).orElseThrow(() -> new ResourceNotFoundException("EditorialId " + editorialId + " not found"));
    }
    
    @DeleteMapping("/editoriales/{editorialId}")
    public ResponseEntity<?> deleteEditorial(@PathVariable Long editorialId) {
        return editorialRepository.findById(editorialId).map(lector -> {
        	editorialRepository.delete(lector);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("EditorialId " + editorialId + " not found"));
    }

}
