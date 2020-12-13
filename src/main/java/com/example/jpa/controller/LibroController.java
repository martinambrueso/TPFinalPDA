package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Libro;
import com.example.jpa.repository.AutorRepository;
import com.example.jpa.repository.CategoriaRepository;
import com.example.jpa.repository.EditorialRepository;
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
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private EditorialRepository editorialRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @GetMapping("/libros")
    public Page<Libro> getAllLibros(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    @PostMapping("/libros")
    public Libro createLibro(@Valid @RequestBody Libro libroRequest) {
    	
    	editorialRepository.findById(libroRequest.getIdEditorial()).map(editorial -> {
    		libroRequest.setEditorial(editorial);
            return libroRequest;
        }).orElseThrow(() -> new ResourceNotFoundException("EditorialId " + libroRequest.getIdEditorial() + " not found"));
    	
    	categoriaRepository.findById(libroRequest.getIdCategoria()).map(categoria -> {
    		libroRequest.setCategoria(categoria);
            return libroRequest;
        }).orElseThrow(() -> new ResourceNotFoundException("CategoriaId " + libroRequest.getIdCategoria() + " not found"));
    	
    	autorRepository.findById(libroRequest.getIdAutor()).map(autor -> {
    		libroRequest.setAutor(autor);
            return libroRequest;
        }).orElseThrow(() -> new ResourceNotFoundException("AutorId " + libroRequest.getIdAutor() + " not found"));
    	
        return libroRepository.save(libroRequest);
    }

    @PutMapping("/libros/{libroId}")
    public Libro updateLibro(@PathVariable Long libroId, @Valid @RequestBody Libro libroRequest) {
        return libroRepository.findById(libroId).map(libro -> {
        	libro.setDescripcion(libroRequest.getDescripcion());
        	libro.setFechaLanzamiento(libroRequest.getFechaLanzamiento());
        	libro.setIdioma(libroRequest.getIdioma());
        	libro.setIsbn(libroRequest.getIsbn());
        	libro.setPaginas(libroRequest.getPaginas());
        	libro.setPeso(libroRequest.getPeso());
        	libro.setTitulo(libroRequest.getTitulo());
            return libroRepository.save(libro);
        }).orElseThrow(() -> new ResourceNotFoundException("LibroId" + libroId + " not found"));
    }
    
    @DeleteMapping("/libros/{libroId}")
    public ResponseEntity<?> deleteLibro(@PathVariable Long libroId) {
        return libroRepository.findById(libroId).map(libro -> {
        	libroRepository.delete(libro);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("LibroId " + libroId + " not found"));
    }

}
