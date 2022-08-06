package ejemplo.appexamenes.servicios;

import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.entidades.ExamenAlumno;

/**
 * Service Interface for managing {@link ExamenAlumno}.
 */
public interface ServicioExamenesAlumnos {
    /**
     * Save a examenAlumno.
     *
     * @param examenAlumno the entity to save.
     * @return the persisted entity.
     */
    ExamenAlumno save(ExamenAlumno examenAlumno);

    /**
     * Updates a examenAlumno.
     *
     * @param examenAlumno the entity to update.
     * @return the persisted entity.
     */
    ExamenAlumno update(ExamenAlumno examenAlumno);

    /**
     * Partially updates a examenAlumno.
     *
     * @param examenAlumno the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ExamenAlumno> partialUpdate(ExamenAlumno examenAlumno);

    /**
     * Get all the examenAlumnos.
     *
     * @return the list of entities.
     */
    List<ExamenAlumno> findAll();
    
    List<ExamenAlumno> findPorAlumno(Integer idAlumno);
    
    List<ExamenAlumno> findPorExamen(Integer idExamen);

    /**
     * Get the "id" examenAlumno.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExamenAlumno> findOne(Integer id);

    /**
     * Delete the "id" examenAlumno.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
