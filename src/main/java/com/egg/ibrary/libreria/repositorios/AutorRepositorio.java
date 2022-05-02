
package com.egg.ibrary.libreria.repositorios;

import com.egg.ibrary.libreria.entidades.Autor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    @Query("SELECT a FROM Autor a WHERE a.id = :PARAMETRO")
    public Autor nombreQuery(@Param("PARAMETRO") String id);

    // EJEMPLO DE UNA QUERY CON JOIN
    // • para formular las queries utilizamos lenguaje de consulta JPQL
    // • para que sean dinámicas utilizamos la notación @Param, con la cual indicamos con un alias un valor
    //    de la query que tomará el valor que se toma a continuación en el parámetro(VALOR)
    @Query("SELECT a FROM Libro a JOIN Autor u WHERE u.nombre = :nombre")
    public List<Autor> buscarPorNombre(@Param("nombre") String VALOR);

    // EJEMPLO DE UNA QUERY MULTITABLAS SIN JOIN
   // @Query("SELECT u FROM Usuario u WHERE u.direccion.provincia = :provincia ")
   // public List<Usuario> buscarPorProvincia(@Param("provincia") Provincia provincia);
    
    
}
