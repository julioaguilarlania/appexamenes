package ejemplo.appexamenes.servicios.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.entidades.ExamenAlumno;
import ejemplo.appexamenes.repositorios.RepositorioAlumnos;
import ejemplo.appexamenes.repositorios.RepositorioExamenes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ejemplo.appexamenes.repositorios.RepositorioExamenesAlumnos;
import ejemplo.appexamenes.servicios.ServicioExamenesAlumnos;

/**
 * Service Implementation for managing {@link ExamenAlumno}.
 */
@Service
@Transactional
public class ServicioExamenesAlumnosImpl implements ServicioExamenesAlumnos {

    private final Logger log = LoggerFactory.getLogger(ServicioExamenesAlumnosImpl.class);

    private final RepositorioExamenesAlumnos repoExamenesAlumnos;
    private final RepositorioExamenes repoExamenes;
    private final RepositorioAlumnos repoAlumnos;

    public ServicioExamenesAlumnosImpl(RepositorioExamenesAlumnos examenAlumnoRepository,
            RepositorioExamenes repoExamenes,
            RepositorioAlumnos repoAlumnos) {
        this.repoExamenesAlumnos = examenAlumnoRepository;
        this.repoExamenes = repoExamenes;
        this.repoAlumnos = repoAlumnos;
    }

    @Override
    public ExamenAlumno save(ExamenAlumno examenAlumno) {
        log.debug("Request to save ExamenAlumno : {}", examenAlumno);
        Optional<ExamenAlumno> exAl = repoExamenesAlumnos.buscarPorIds(examenAlumno.getIdExamen(), examenAlumno.getIdAlumno());
        if (exAl.isPresent()) {
            log.warn("Ya existia examen {} y alumno {}", examenAlumno.getIdExamen(), examenAlumno.getIdAlumno());
            throw new IllegalArgumentException("Ya existia examen alumno");
        }
        examenAlumno.setMomentoInicio(new Date());
        return repoExamenesAlumnos.save(examenAlumno);
    }

    @Override
    public ExamenAlumno update(ExamenAlumno examenAlumno) {
        log.debug("Request to save ExamenAlumno : {}", examenAlumno);
        return repoExamenesAlumnos.save(examenAlumno);
    }

    @Override
    public Optional<ExamenAlumno> partialUpdate(ExamenAlumno examenAlumno) {
        log.debug("Request to partially update ExamenAlumno : {}", examenAlumno);

        return repoExamenesAlumnos
            .findById(examenAlumno.getIdExamenAlumno())
            .map(existingExamenAlumno -> {
                if (examenAlumno.getMomentoInicio() != null) {
                    existingExamenAlumno.setMomentoInicio(examenAlumno.getMomentoInicio());
                }
                if (examenAlumno.getMomentoFin() != null) {
                    existingExamenAlumno.setMomentoFin(examenAlumno.getMomentoFin());
                }
                if (examenAlumno.getCalificacion() != null) {
                    existingExamenAlumno.setCalificacion(examenAlumno.getCalificacion());
                }

                return existingExamenAlumno;
            })
            .map(repoExamenesAlumnos::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamenAlumno> findAll() {
        log.debug("Request to get all ExamenAlumnos");
        return repoExamenesAlumnos.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExamenAlumno> findOne(Integer id) {
        log.debug("Request to get ExamenAlumno : {}", id);
        return repoExamenesAlumnos.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete ExamenAlumno : {}", id);
        repoExamenesAlumnos.deleteById(id);
    }

    @Override
    public List<ExamenAlumno> findPorAlumno(Integer idAlumno) {
        List<ExamenAlumno> eas = repoExamenesAlumnos.buscarPorAlumno(idAlumno);
        for (ExamenAlumno ea : eas) {
            ea.setExamen(repoExamenes.findById(ea.getIdExamen()).get());
        }
        return eas;
    }

    @Override
    public List<ExamenAlumno> findPorExamen(Integer idExamen) {
        List<ExamenAlumno> eas = repoExamenesAlumnos.buscarPorExamen(idExamen);
        for (ExamenAlumno ea : eas) {
            ea.setAlumno(repoAlumnos.findById(ea.getIdAlumno()).get());
        }
        return repoExamenesAlumnos.buscarPorExamen(idExamen);
    }
}
