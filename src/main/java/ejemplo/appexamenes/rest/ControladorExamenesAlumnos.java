package ejemplo.appexamenes.rest;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.dto.AlumnoCalificacion;
import ejemplo.appexamenes.entidades.Alumno;
import ejemplo.appexamenes.entidades.Examen;
import ejemplo.appexamenes.entidades.ExamenAlumno;
import ejemplo.appexamenes.repositorios.RepositorioAlumnos;
import ejemplo.appexamenes.repositorios.RepositorioExamenes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ejemplo.appexamenes.repositorios.RepositorioExamenesAlumnos;
import ejemplo.appexamenes.servicios.ServicioExamenesAlumnos;

/**
 * REST controller for managing {@link ejemplo.appexamenes.entidades.ExamenAlumno}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin()
public class ControladorExamenesAlumnos {

    private final Logger log = LoggerFactory.getLogger(ControladorExamenesAlumnos.class);

    private final ServicioExamenesAlumnos examenAlumnoService;

    private final RepositorioExamenesAlumnos examenAlumnoRepository;
    private final RepositorioExamenes examenRepository;
    private final RepositorioAlumnos alumnoRepository;

    public ControladorExamenesAlumnos(ServicioExamenesAlumnos examenAlumnoService, 
            RepositorioExamenesAlumnos examenAlumnoRepository,
            RepositorioExamenes examenRepository,
            RepositorioAlumnos alumnoRepository) {
        this.examenAlumnoService = examenAlumnoService;
        this.examenAlumnoRepository = examenAlumnoRepository;
        this.examenRepository = examenRepository;
        this.alumnoRepository = alumnoRepository;
    }

    /**
     * {@code POST  /examen-alumnos} : Create a new examenAlumno.
     *
     * @param examenAlumno the examenAlumno to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new examenAlumno, or with status {@code 400 (Bad Request)} if the examenAlumno has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/examen-alumnos")
    public ResponseEntity<ExamenAlumno> createExamenAlumno(@RequestBody ExamenAlumno examenAlumno) throws URISyntaxException {
        log.debug("REST request to save ExamenAlumno : {}", examenAlumno);
        ExamenAlumno result = examenAlumnoService.save(examenAlumno);
        return ResponseEntity
            .created(new URI("/api/examen-alumnos/" + result.getIdExamenAlumno()))
            .body(result);
    }

    /**
     * {@code PUT  /examen-alumnos/:id} : Updates an existing examenAlumno.
     *
     * @param id the id of the examenAlumno to save.
     * @param examenAlumno the examenAlumno to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examenAlumno,
     * or with status {@code 400 (Bad Request)} if the examenAlumno is not valid,
     * or with status {@code 500 (Internal Server Error)} if the examenAlumno couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/examen-alumnos/{id}")
    public ResponseEntity<ExamenAlumno> updateExamenAlumno(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExamenAlumno examenAlumno
    ) throws URISyntaxException {
        log.debug("REST request to update ExamenAlumno : {}, {}", id, examenAlumno);

        ExamenAlumno result = examenAlumnoService.update(examenAlumno);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /examen-alumnos/:id} : Partial updates given fields of an existing examenAlumno, field will ignore if it is null
     *
     * @param id the id of the examenAlumno to save.
     * @param examenAlumno the examenAlumno to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examenAlumno,
     * or with status {@code 400 (Bad Request)} if the examenAlumno is not valid,
     * or with status {@code 404 (Not Found)} if the examenAlumno is not found,
     * or with status {@code 500 (Internal Server Error)} if the examenAlumno couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/examen-alumnos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ExamenAlumno> partialUpdateExamenAlumno(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExamenAlumno examenAlumno
    ) throws URISyntaxException {
        log.debug("REST request to partial update ExamenAlumno partially : {}, {}", id, examenAlumno);
        Optional<ExamenAlumno> result = examenAlumnoService.partialUpdate(examenAlumno);

        return ResponseEntity.ok(result.get());
    }

    /**
     * {@code GET  /examen-alumnos} : get all the examenAlumnos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examenAlumnos in body.
     */
    @GetMapping("/examen-alumnos")
    public List<ExamenAlumno> getAllExamenAlumnos() {
        log.debug("REST request to get all ExamenAlumnos");
        return examenAlumnoService.findAll();
    }

    @GetMapping(value = "/examen-alumnos", params={"idAlumno"})
    public List<ExamenAlumno> getExamenAlumnosPorAlumno(@RequestParam("idAlumno") Integer idAlumno) {
        log.debug("REST request to get all ExamenAlumnos");
        return examenAlumnoService.findPorAlumno(idAlumno);
    }

    @GetMapping(value = "/examen-alumnos", params={"idExamen"})
    public List<ExamenAlumno> getExamenAlumnosPorExamen(@RequestParam("idExamen") Integer idExamen) {
        log.debug("REST request to get all ExamenAlumnos");
        return examenAlumnoService.findPorExamen(idExamen);
    }
    
    @GetMapping(value = "/examen-alumnos/completo", params={"idExamen"})
    public List<AlumnoCalificacion> getAlumnosPorExamen(@RequestParam("idExamen") Integer idExamen) {
        Examen ex = examenRepository.findById(idExamen).get();
        List<Object[]> resultados = alumnoRepository.getPorExamen(idExamen, ex.getIdExperienciaEducativa().getIdExperienciaEducativa());
        return resultados.stream().map(r -> new AlumnoCalificacion(
                (Integer) r[0], (String) r[1], (String) r[2], (BigDecimal) r[3], (Date) r[4])).toList();
    }

    /**
     * {@code DELETE  /examen-alumnos/:id} : delete the "id" examenAlumno.
     *
     * @param id the id of the examenAlumno to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/examen-alumnos/{id}")
    public ResponseEntity<Void> deleteExamenAlumno(@PathVariable Integer id) {
        log.debug("REST request to delete ExamenAlumno : {}", id);
        examenAlumnoService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
    
    @GetMapping(value = "/alumno", params={"idee"})
    public List<Alumno> getAllExamenAlumnos(@RequestParam("idee") Integer idExpEd) {
        log.debug("REST request to get all Alumnos");
        return alumnoRepository.buscarPorExpEd(idExpEd);
    }
}
