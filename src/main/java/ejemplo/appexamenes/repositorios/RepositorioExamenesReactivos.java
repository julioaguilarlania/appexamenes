package ejemplo.appexamenes.repositorios;

import java.util.List;
import ejemplo.appexamenes.entidades.ExamenReactivo;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExamenReactivo entity.
 */
@Repository
public interface RepositorioExamenesReactivos extends JpaRepository<ExamenReactivo, Integer> {

    List<ExamenReactivo> findByIdExamen(Integer idExamen);
    
    @Modifying
    @Query(value="DELETE FROM examen_reactivo WHERE id_examen = :idex", nativeQuery = true)
    void borrarPorExamen(@Param("idex") Integer idExamen);
}
