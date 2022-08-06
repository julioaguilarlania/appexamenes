package ejemplo.appexamenes.servicios;

import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.dto.RespuestasExamen;
import ejemplo.appexamenes.entidades.RespuestaReactivo;

/**
 * Service Interface for managing {@link RespuestaReactivo}.
 */
public interface ServicioRespuestasReactivos {
    /**
     * Save a respuestaReactivo.
     *
     * @param respuestaReactivo the entity to save.
     * @return the persisted entity.
     */
    RespuestaReactivo save(RespuestaReactivo respuestaReactivo);

    /**
     * Updates a respuestaReactivo.
     *
     * @param respuestaReactivo the entity to update.
     * @return the persisted entity.
     */
    RespuestaReactivo update(RespuestaReactivo respuestaReactivo);

    /**
     * Partially updates a respuestaReactivo.
     *
     * @param respuestaReactivo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RespuestaReactivo> partialUpdate(RespuestaReactivo respuestaReactivo);

    /**
     * Get all the respuestaReactivos.
     *
     * @return the list of entities.
     */
    List<RespuestaReactivo> findAll();

    /**
     * Delete the "id" respuestaReactivo.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);

    public void guardar(RespuestasExamen respuestas);
}
