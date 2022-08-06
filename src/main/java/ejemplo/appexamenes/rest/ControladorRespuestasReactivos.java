package ejemplo.appexamenes.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.dto.RespuestasExamen;
import ejemplo.appexamenes.entidades.RespuestaReactivo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ejemplo.appexamenes.repositorios.RepositorioRespuestasReactivos;
import ejemplo.appexamenes.servicios.ServicioRespuestasReactivos;

/**
 * REST controller for managing {@link ejemplo.appexamenes.entidades.RespuestaReactivo}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin()
public class ControladorRespuestasReactivos {

    private final Logger log = LoggerFactory.getLogger(ControladorRespuestasReactivos.class);

    private final ServicioRespuestasReactivos respuestaReactivoService;

    private final RepositorioRespuestasReactivos respuestaReactivoRepository;

    public ControladorRespuestasReactivos(
        ServicioRespuestasReactivos respuestaReactivoService,
        RepositorioRespuestasReactivos respuestaReactivoRepository
    ) {
        this.respuestaReactivoService = respuestaReactivoService;
        this.respuestaReactivoRepository = respuestaReactivoRepository;
    }

    /**
     * {@code POST  /respuesta-reactivos} : Create a new respuestaReactivo.
     *
     * @param respuestaReactivo the respuestaReactivo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new respuestaReactivo, or with status {@code 400 (Bad Request)} if the respuestaReactivo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/respuesta-reactivos")
    public ResponseEntity<RespuestaReactivo> createRespuestaReactivo(@RequestBody RespuestasExamen respuestas) {
        respuestaReactivoService.guardar(respuestas);
        return ResponseEntity.ok().build();
    }

    /**
     * {@code PUT  /respuesta-reactivos/:id} : Updates an existing respuestaReactivo.
     *
     * @param id the id of the respuestaReactivo to save.
     * @param respuestaReactivo the respuestaReactivo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated respuestaReactivo,
     * or with status {@code 400 (Bad Request)} if the respuestaReactivo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the respuestaReactivo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/respuesta-reactivos/{id}")
    public ResponseEntity<RespuestaReactivo> updateRespuestaReactivo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RespuestaReactivo respuestaReactivo
    ) throws URISyntaxException {
        log.debug("REST request to update RespuestaReactivo : {}, {}", id, respuestaReactivo);

        RespuestaReactivo result = respuestaReactivoService.update(respuestaReactivo);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /respuesta-reactivos/:id} : Partial updates given fields of an existing respuestaReactivo, field will ignore if it is null
     *
     * @param id the id of the respuestaReactivo to save.
     * @param respuestaReactivo the respuestaReactivo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated respuestaReactivo,
     * or with status {@code 400 (Bad Request)} if the respuestaReactivo is not valid,
     * or with status {@code 404 (Not Found)} if the respuestaReactivo is not found,
     * or with status {@code 500 (Internal Server Error)} if the respuestaReactivo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/respuesta-reactivos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RespuestaReactivo> partialUpdateRespuestaReactivo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RespuestaReactivo respuestaReactivo
    ) throws URISyntaxException {
        log.debug("REST request to partial update RespuestaReactivo partially : {}, {}", id, respuestaReactivo);

        Optional<RespuestaReactivo> result = respuestaReactivoService.partialUpdate(respuestaReactivo);

        return ResponseEntity.ok(result.get());
    }

    /**
     * {@code GET  /respuesta-reactivos} : get all the respuestaReactivos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of respuestaReactivos in body.
     */
    @GetMapping("/respuesta-reactivos")
    public List<RespuestaReactivo> getAllRespuestaReactivos() {
        log.debug("REST request to get all RespuestaReactivos");
        return respuestaReactivoService.findAll();
    }

    /**
     * {@code DELETE  /respuesta-reactivos/:id} : delete the "id" respuestaReactivo.
     *
     * @param id the id of the respuestaReactivo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/respuesta-reactivos/{id}")
    public ResponseEntity<Void> deleteRespuestaReactivo(@PathVariable Integer id) {
        log.debug("REST request to delete RespuestaReactivo : {}", id);
        respuestaReactivoService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
