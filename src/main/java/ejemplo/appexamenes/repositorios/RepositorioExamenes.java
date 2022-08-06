package ejemplo.appexamenes.repositorios;

import java.util.List;
import ejemplo.appexamenes.entidades.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Examen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RepositorioExamenes extends JpaRepository<Examen, Integer> {

    @Query(value = "SELECT * FROM examen e " +
"WHERE e.cerrado = false AND e.id_examen NOT IN ( " +
" SELECT id_examen FROM examen_alumno ea " +
" WHERE ea.id_alumno = :idal AND ea.momento_fin IS NOT NULL)", nativeQuery = true)
    List<Examen> buscarDisponiblesParaAlumno(@Param("idal") Integer idAlumno);
}
