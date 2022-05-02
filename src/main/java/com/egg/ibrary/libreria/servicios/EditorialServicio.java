package com.egg.ibrary.libreria.servicios;

import com.egg.ibrary.libreria.entidades.Editorial;
import com.egg.ibrary.libreria.error.ErrorServicio;
import com.egg.ibrary.libreria.repositorios.EditorialRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private final EditorialRepositorio editorialRepositorio;

    public EditorialServicio(EditorialRepositorio editorialRepositorio) {
        this.editorialRepositorio = editorialRepositorio;
    }

    @Transactional(propagation = Propagation.NESTED)
    public void agregarEditorial(String nombre, Boolean alta) throws ErrorServicio {
        validar(nombre, nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(Boolean.TRUE);

        editorialRepositorio.save(editorial);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void modificarAutor(String nombre, String id) throws ErrorServicio {
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        } else {
            throw new ErrorServicio("no exite autor con ese nombre o id");
        }

    }

    /* public void listarAutor(Autor autor){
        
        autorRepositorio.findById(autor.getId());
    }
     */
    public void consultar(String id) throws ErrorServicio {
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
        } else {
            throw new ErrorServicio("no hay un autor con ese id");

        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void eliminar(String id) throws ErrorServicio {
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(Boolean.FALSE);
            editorialRepositorio.save(editorial);
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
