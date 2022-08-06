package ejemplo.appexamenes.servicios;

import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.dto.SeleccionReactivos;
import ejemplo.appexamenes.entidades.ExamenReactivo;

/**
 * Service Interface for managing {@link ExamenReactivo}.
 */
public interface ServicioExamenesReactivos {
    /**
     * Save a examenReactivo.
     *
     * @param seleccion the entities to save.
     * @return the persisted entity.
     */
    void save(SeleccionReactivos seleccion);

    /**
     * Updates a examenReactivo.
     *
     * @param examenReactivo the entity to update.
     * @return the persisted entity.
     */
    ExamenReactivo update(ExamenReactivo examenReactivo);

    /**
     * Partially updates a examenReactivo.
     *
     * @param examenReactivo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ExamenReactivo> partialUpdate(ExamenReactivo examenReactivo);

    /**
     * Get all the examenReactivos.
     *
     * @return the list of entities.
     */
    SeleccionReactivos findByIdExamen(Integer idExamen);


    /**
     * Delete the "id" examenReactivo.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
