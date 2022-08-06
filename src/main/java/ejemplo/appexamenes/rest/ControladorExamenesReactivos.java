package ejemplo.appexamenes.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.dto.SeleccionReactivos;
import ejemplo.appexamenes.entidades.ExamenReactivo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ejemplo.appexamenes.servicios.ServicioExamenesReactivos;
import ejemplo.appexamenes.repositorios.RepositorioExamenesReactivos;

/**
 * REST controller for managing {@link ejemplo.appexamenes.entidades.ExamenReactivo}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin()
public class ControladorExamenesReactivos {

    private final Logger log = LoggerFactory.getLogger(ControladorExamenesReactivos.class);

    private final ServicioExamenesReactivos examenReactivoService;

    private final RepositorioExamenesReactivos examenReactivoRepository;

    public ControladorExamenesReactivos(ServicioExamenesReactivos examenReactivoService, RepositorioExamenesReactivos examenReactivoRepository) {
        this.examenReactivoService = examenReactivoService;
        this.examenReactivoRepository = examenReactivoRepository;
    }

    /**
     * {@code POST  /examen-reactivos} : Create a new examenReactivo.
     *
     * @param examenReactivo the examenReactivo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new examenReactivo, or with status {@code 400 (Bad Request)} if the examenReactivo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/examen-reactivos")
    public ResponseEntity createExamenReactivo(@RequestBody SeleccionReactivos seleccion) throws URISyntaxException {
        log.debug("REST request to save SeleccionReactivos : {}", seleccion);
        examenReactivoService.save(seleccion);
        return ResponseEntity.ok().build();
    }

    /**
     * {@code PUT  /examen-reactivos/:id} : Updates an existing examenReactivo.
     *
     * @param id the id of the examenReactivo to save.
     * @param examenReactivo the examenReactivo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examenReactivo,
     * or with status {@code 400 (Bad Request)} if the examenReactivo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the examenReactivo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/examen-reactivos/{id}")
    public ResponseEntity<ExamenReactivo> updateExamenReactivo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExamenReactivo examenReactivo
    ) throws URISyntaxException {
        log.debug("REST request to update ExamenReactivo : {}, {}", id, examenReactivo);
        ExamenReactivo result = examenReactivoService.update(examenReactivo);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /examen-reactivos/:id} : Partial updates given fields of an existing examenReactivo, field will ignore if it is null
     *
     * @param id the id of the examenReactivo to save.
     * @param examenReactivo the examenReactivo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examenReactivo,
     * or with status {@code 400 (Bad Request)} if the examenReactivo is not valid,
     * or with status {@code 404 (Not Found)} if the examenReactivo is not found,
     * or with status {@code 500 (Internal Server Error)} if the examenReactivo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/examen-reactivos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ExamenReactivo> partialUpdateExamenReactivo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExamenReactivo examenReactivo
    ) throws URISyntaxException {
        log.debug("REST request to partial update ExamenReactivo partially : {}, {}", id, examenReactivo);

        Optional<ExamenReactivo> result = examenReactivoService.partialUpdate(examenReactivo);
        return ResponseEntity.ok(result.get());

    }

    /**
     * {@code GET  /examen-reactivos} : get all the examenReactivos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examenReactivos in body.
     */ 
    @GetMapping(value = "/examen-reactivos", params = {"idExamen"})
    public SeleccionReactivos getAllExamenReactivos(@RequestParam("idExamen") Integer idExamen) {
        log.debug("REST request to get all ExamenReactivos");
        return examenReactivoService.findByIdExamen(idExamen);
    }


    /**
     * {@code DELETE  /examen-reactivos/:id} : delete the "id" examenReactivo.
     *
     * @param id the id of the examenReactivo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/examen-reactivos/{id}")
    public ResponseEntity<Void> deleteExamenReactivo(@PathVariable Integer id) {
        log.debug("REST request to delete ExamenReactivo : {}", id);
        examenReactivoService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
