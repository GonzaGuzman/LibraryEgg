package com.egg.ibrary.libreria.repositorios;


import com.egg.ibrary.libreria.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
        @Query("SELECT a FROM Editorial a WHERE a.id = :PARAMETRO")
    public Editorial nombreQuery(@Param("PARAMETRO") String id);

    // EJEMPLO DE UNA QUERY CON JOIN
    // • para formular las queries utilizamos lenguaje de consulta JPQL
    // • para que sean dinámicas utilizamos la notación @Param, con la cual indicamos con un alias un valor
    //    de la query que tomará el valor que se toma a continuación en el parámetro(VALOR)
    @Query("SELECT a FROM Libro a JOIN Editorial u WHERE u.nombre = :nombre")
    public List<Editorial> buscarPorNombre(@Param("nombre") String VALOR);

}
