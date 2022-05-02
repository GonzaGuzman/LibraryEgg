/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ibrary.libreria.servicios;

import com.egg.ibrary.libreria.entidades.Autor;
import com.egg.ibrary.libreria.error.ErrorServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egg.ibrary.libreria.repositorios.AutorRepositorio;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private final AutorRepositorio autorRepositorio;

    public AutorServicio(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }

    @Transactional(propagation = Propagation.NESTED)
    public void agregarAutor(String nombre, Boolean alta) throws ErrorServicio {
        validar(nombre, nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(Boolean.TRUE);

        autorRepositorio.save(autor);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void modificarAutor(String nombre, String id) throws ErrorServicio {
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("no exite autor con ese nombre o id");
        }

    }

    /* public void listarAutor(Autor autor){
        
        autorRepositorio.findById(autor.getId());
    }
     */
    public void consultar(String id) throws ErrorServicio {
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
        } else {
            throw new ErrorServicio("no hay un autor con ese id");

        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void eliminar(String id) throws ErrorServicio {
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(Boolean.FALSE);
            autorRepositorio.save(autor);
        } else {
            throw new ErrorServicio("no hay un autor con ese id");
        }
    }

    public void validar(String nombre, String id) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El autor debe tener un nombre");
        }

        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("El autor debe tener un id");
        }

    }

}
