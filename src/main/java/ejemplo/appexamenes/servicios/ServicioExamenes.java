package ejemplo.appexamenes.servicios;

import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.entidades.Examen;

/**
 * Service Interface for managing {@link Examen}.
 */
public interface ServicioExamenes {
    /**
     * Save a examen.
     *
     * @param examen the entity to save.
     * @return the persisted entity.
     */
    Examen save(Examen examen);

    /**
     * Updates a examen.
     *
     * @param examen the entity to update.
     * @return the persisted entity.
     */
    Examen update(Examen examen);

    /**
     * Partially updates a examen.
     *
     * @param examen the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Examen> partialUpdate(Examen examen);

    /**
     * Get all the examen.
     *
     * @return the list of entities.
     */
    List<Examen> findAll();

    /**
     * Get the "id" examen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Examen> findOne(Integer id);

    /**
     * Delete the "id" examen.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
