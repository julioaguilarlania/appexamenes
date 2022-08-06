package ejemplo.appexamenes.repositorios;

import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.entidades.ExamenAlumno;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExamenAlumno entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RepositorioExamenesAlumnos extends JpaRepository<ExamenAlumno, Integer> {
    @Query(value = "SELECT * FROM examen_alumno WHERE id_examen = :idex AND id_alumno = :idal",
            nativeQuery = true)
    Optional<ExamenAlumno> buscarPorIds(@Param("idex")Integer idExamen,
            @Param("idal") Integer idAlumno);
    
    @Query(value="SELECT * FROM examen_alumno WHERE id_alumno = :idal", nativeQuery = true)
    List<ExamenAlumno> buscarPorAlumno(@Param("idal") Integer idAlumno);
    
    @Query(value="SELECT * FROM examen_alumno WHERE id_examen = :idex", nativeQuery = true)
    List<ExamenAlumno> buscarPorExamen(@Param("idex") Integer idExamen);
}
