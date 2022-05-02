/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ibrary.libreria.servicios;

import com.egg.ibrary.libreria.entidades.Autor;
import com.egg.ibrary.libreria.entidades.Editorial;
import com.egg.ibrary.libreria.entidades.Libro;
import com.egg.ibrary.libreria.error.ErrorServicio;

import com.egg.ibrary.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gonza
 */
public class LibroServicio {

    @Autowired
    private final LibroRepositorio libroRepositorio;

    public LibroServicio(LibroRepositorio libroRepositorio) {
        this.libroRepositorio = libroRepositorio;
    }

    @Transactional(propagation = Propagation.NESTED)

    public void agregarAutor(Long isbn, String titulo, Boolean alta, Integer anio, Autor autor, Editorial editorial, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes
    ) throws ErrorServicio {
        validar(titulo, titulo, isbn, anio, autor, editorial);
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(libro.getEjemplares());
        libro.setAlta(Boolean.TRUE);
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void modificarLibro(Long isbn, String titulo, Integer anio, Autor autor, Editorial editorial, String id) throws ErrorServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

        } else {
            throw new ErrorServicio("no exite autor con ese nombre o id");
        }

    }

    /* public void listarAutor(Autor autor){
        
        autorRepositorio.findById(autor.getId());
    }
     */
    public void consultar(String id) throws ErrorServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
        } else {
            throw new ErrorServicio("no hay un autor con ese id");

        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void eliminar(String id) throws ErrorServicio {
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(Boolean.FALSE);
            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("no hay un autor con ese id");
        }
    }

    public void validar(String titulo, String id, Long isbn, Integer anio, Autor autor, Editorial editorial) throws ErrorServicio {
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El libro debe tener un nombre");
        }
        if (id == null) {
            throw new ErrorServicio(" El libro debe tener un Id");
        }
        if (isbn == null) {
            throw new ErrorServicio("El libro debe tener un Isbn");
        }
        if (anio == null) {
            throw new ErrorServicio("El libro debe tener un año");
        }
        if (autor == null) {
            throw new ErrorServicio("El libro debe tener un autor");
        }
        if (editorial == null) {
            throw new ErrorServicio("El libro debe tener una editorial");
        }
    }
    
    @Transactional (readOnly = true)
    public List<Libro> listarTodo(){
        List<Libro> libros = libroRepositorio.findAll();
    return libros;
    }
      
}
