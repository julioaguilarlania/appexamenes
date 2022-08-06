package ejemplo.appexamenes.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.entidades.Examen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ejemplo.appexamenes.repositorios.RepositorioExamenes;
import ejemplo.appexamenes.servicios.ServicioExamenes;

/**
 * REST controller for managing {@link ejemplo.appexamenes.entidades.Examen}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin()
public class ControladorExamenes {

    private final Logger log = LoggerFactory.getLogger(ControladorExamenes.class);

    private final ServicioExamenes examenService;

    private final RepositorioExamenes examenRepository;

    public ControladorExamenes(ServicioExamenes examenService, RepositorioExamenes examenRepository) {
        this.examenService = examenService;
        this.examenRepository = examenRepository;
    }

    /**
     * {@code POST  /examen} : Create a new examen.
     *
     * @param examen the examen to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new examen, or with status {@code 400 (Bad Request)} if the examen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/examen")
    public ResponseEntity<Examen> createExamen(@RequestBody Examen examen) throws URISyntaxException {
        log.debug("REST request to save Examen : {}", examen);
        Examen result = examenService.save(examen);
        return ResponseEntity
            .created(new URI("/api/examen/" + result.getIdExamen()))
            .body(result);
    }

    /**
     * {@code PUT  /examen/:id} : Updates an existing examen.
     *
     * @param id the id of the examen to save.
     * @param examen the examen to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examen,
     * or with status {@code 400 (Bad Request)} if the examen is not valid,
     * or with status {@code 500 (Internal Server Error)} if the examen couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/examen/{id}")
    public ResponseEntity<Examen> updateExamen(@PathVariable(value = "id", required = false) final Long id, @RequestBody Examen examen)
        throws URISyntaxException {
        log.debug("REST request to update Examen : {}, {}", id, examen);

        Examen result = examenService.update(examen);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /examen/:id} : Partial updates given fields of an existing examen, field will ignore if it is null
     *
     * @param id the id of the examen to save.
     * @param examen the examen to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examen,
     * or with status {@code 400 (Bad Request)} if the examen is not valid,
     * or with status {@code 404 (Not Found)} if the examen is not found,
     * or with status {@code 500 (Internal Server Error)} if the examen couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/examen/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Examen> partialUpdateExamen(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody Examen examen
    ) throws URISyntaxException {
        log.debug("REST request to partial update Examen partially : {}, {}", id, examen);

        Optional<Examen> result = examenService.partialUpdate(examen);
        return ResponseEntity.ok(result.get());
    }

    /**
     * {@code GET  /examen} : get all the examen.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examen in body.
     */
    @GetMapping("/examen")
    public List<Examen> getAllExamen() {
        log.debug("REST request to get all Examen");
        return examenService.findAll();
    }
    
    @GetMapping(value = "/examendisponibles", params = {"idAlumno"})
    public List<Examen> getExamenesDisponibles(@RequestParam("idAlumno") Integer idAlumno) {
        log.debug("REST request to get all Examen");
        return examenRepository.buscarDisponiblesParaAlumno(idAlumno);
    }

    /**
     * {@code GET  /examen/:id} : get the "id" examen.
     *
     * @param id the id of the examen to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the examen, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/examen/{id}")
    public ResponseEntity<Examen> getExamen(@PathVariable Integer id) {
        log.debug("REST request to get Examen : {}", id);
        Optional<Examen> examen = examenService.findOne(id);
        if (examen.isPresent()) {
            return ResponseEntity.ok(examen.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code DELETE  /examen/:id} : delete the "id" examen.
     *
     * @param id the id of the examen to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/examen/{id}")
    public ResponseEntity<Void> deleteExamen(@PathVariable Integer id) {
        log.debug("REST request to delete Examen : {}", id);
        examenService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
