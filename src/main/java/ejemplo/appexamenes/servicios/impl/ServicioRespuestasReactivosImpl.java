package ejemplo.appexamenes.servicios.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.dto.RespuestasExamen;
import ejemplo.appexamenes.entidades.ExamenAlumno;
import ejemplo.appexamenes.entidades.Reactivo;
import ejemplo.appexamenes.entidades.RespuestaReactivo;
import ejemplo.appexamenes.repositorios.RepositorioExamenes;
import ejemplo.appexamenes.repositorios.RepositorioExamenesAlumnos;
import ejemplo.appexamenes.repositorios.RepositorioReactivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ejemplo.appexamenes.repositorios.RepositorioRespuestasReactivos;
import ejemplo.appexamenes.servicios.ServicioRespuestasReactivos;

/**
 * Service Implementation for managing {@link RespuestaReactivo}.
 */
@Service
@Transactional
public class ServicioRespuestasReactivosImpl implements ServicioRespuestasReactivos {

    private final Logger log = LoggerFactory.getLogger(ServicioRespuestasReactivosImpl.class);

    private final RepositorioRespuestasReactivos repoRespuestasReactivos;
    private final RepositorioExamenesAlumnos repoExamenesAlumnos;
    private final RepositorioReactivos repoReactivos;

    public ServicioRespuestasReactivosImpl(RepositorioRespuestasReactivos respuestaReactivoRepository,
            RepositorioExamenesAlumnos repoExamenesAlumnos,
            RepositorioReactivos repoReactivos) {
        this.repoRespuestasReactivos = respuestaReactivoRepository;
        this.repoExamenesAlumnos = repoExamenesAlumnos;
        this.repoReactivos = repoReactivos;
    }

    @Override
    public RespuestaReactivo save(RespuestaReactivo respuestaReactivo) {
        log.debug("Request to save RespuestaReactivo : {}", respuestaReactivo);
        return repoRespuestasReactivos.save(respuestaReactivo);
    }

    @Override
    public RespuestaReactivo update(RespuestaReactivo respuestaReactivo) {
        log.debug("Request to save RespuestaReactivo : {}", respuestaReactivo);
        return repoRespuestasReactivos.save(respuestaReactivo);
    }

    @Override
    public Optional<RespuestaReactivo> partialUpdate(RespuestaReactivo respuestaReactivo) {
        log.debug("Request to partially update RespuestaReactivo : {}", respuestaReactivo);

        return repoRespuestasReactivos
            .findById(respuestaReactivo.getIdRespuestaReactivo())
            .map(existingRespuestaReactivo -> {
                if (respuestaReactivo.getOpcion() != null) {
                    existingRespuestaReactivo.setOpcion(respuestaReactivo.getOpcion());
                }
                if (respuestaReactivo.getCorrecta() != null) {
                    existingRespuestaReactivo.setCorrecta(respuestaReactivo.getCorrecta());
                }

                return existingRespuestaReactivo;
            })
            .map(repoRespuestasReactivos::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RespuestaReactivo> findAll() {
        log.debug("Request to get all RespuestaReactivos");
        return repoRespuestasReactivos.findAll();
    }


    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RespuestaReactivo : {}", id);
        repoRespuestasReactivos.deleteById(id);
    }

    @Override
    public void guardar(RespuestasExamen respuestas) {
        log.debug("Guardar {} respuestas examen {} alumno {}", 
                respuestas.getRespuestas().size(), respuestas.getIdExamen(), respuestas.getIdAlumno());
        Optional<ExamenAlumno> exAl = repoExamenesAlumnos.buscarPorIds(respuestas.getIdExamen(), respuestas.getIdAlumno());
        if (exAl.isEmpty()) {
            log.error("No se encontro examen {} y alumno {}", respuestas.getIdExamen(), respuestas.getIdAlumno());
            return;
        }
        List<RespuestaReactivo> respList = new ArrayList<>();
        int contadorAciertos = 0;
        int contadorTotal = 0;
        for (RespuestaReactivo rr : respuestas.getRespuestas()) {
            rr.setIdExamenAlumno(exAl.get());
            Optional<Reactivo> or = repoReactivos.findById(rr.getIdReactivo());
            if (or.isPresent()) {
                contadorTotal++;
                if (or.get().getOpcionCorrecta().equals(rr.getOpcion())) {
                    contadorAciertos++;
                    rr.setCorrecta(Boolean.TRUE);
                }
            }
        }
        repoRespuestasReactivos.saveAll(respList);
        if (contadorTotal > 0) {
            double calificacion = contadorAciertos * 10.0 / contadorTotal;
            exAl.get().setCalificacion(BigDecimal.valueOf(calificacion));
        }
        else {
            if (contadorAciertos == 0) {
                exAl.get().setCalificacion(BigDecimal.ZERO);
            }
        }
        exAl.get().setMomentoFin(new Date());
        repoExamenesAlumnos.save(exAl.get());
    }

}
