package ejemplo.appexamenes.repositorios;

import java.util.List;
import ejemplo.appexamenes.dto.AlumnoCalificacion;
import ejemplo.appexamenes.entidades.Alumno;
import ejemplo.appexamenes.entidades.ExamenAlumno;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Alumno entity.
 */
@Repository
public interface RepositorioAlumnos extends JpaRepository<Alumno, Integer> {

    @Query(value = "SELECT t1.*, t2.calificacion, t2.momento_fin FROM " +
"(SELECT al.id_alumno, al.nombres, al.apellidos " +
"FROM alumno al WHERE al.id_experiencia_educativa = :idee) t1 " +
"LEFT JOIN " +
"(SELECT ea.calificacion, ea.momento_fin, ea.id_alumno " +
"FROM examen_alumno ea WHERE ea.id_examen = :idex) t2 " +
"ON t1.id_alumno = t2.id_alumno", nativeQuery = true)
    List<Object[]> getPorExamen(
            @Param("idex") Integer idExamen, 
            @Param("idee") Integer idExperienciaEducativa);

    @Query(value = "SELECT * FROM alumno WHERE id_experiencia_educativa = :idee", nativeQuery = true)
    public List<Alumno> buscarPorExpEd(@Param("idee") Integer idExpEd);
}
