package ejemplo.appexamenes.servicios.impl;

import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.entidades.Examen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ejemplo.appexamenes.repositorios.RepositorioExamenes;
import ejemplo.appexamenes.servicios.ServicioExamenes;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
 * Service Implementation for managing {@link Examen}.
 */
@Service
@Transactional
public class ServicioExamenesImpl implements ServicioExamenes {

    private final Logger log = LoggerFactory.getLogger(ServicioExamenesImpl.class);

    private final RepositorioExamenes examenRepository;

    public ServicioExamenesImpl(RepositorioExamenes examenRepository) {
        this.examenRepository = examenRepository;
    }

    @Override
    public Examen save(Examen examen) {
        log.debug("Request to save Examen : {}", examen);
        log.debug("Fecha examen: {} {}", examen.getMomentoApertura(), examen.getMomentoCierre());
        if (!StringUtils.hasText(examen.getNombre()) ||
                examen.getMomentoApertura().after(examen.getMomentoCierre())) {
            throw new IllegalArgumentException("Momento de cierre debe ser posterior a apertura");
        }
        examen.setCerrado(Boolean.FALSE);
        return examenRepository.save(examen);
    }

    @Override
    public Examen update(Examen examen) {
        log.debug("Request to save Examen : {}", examen);
        return examenRepository.save(examen);
    }

    @Override
    public Optional<Examen> partialUpdate(Examen examen) {
        log.debug("Request to partially update Examen : {}", examen);

        return examenRepository
            .findById(examen.getIdExamen())
            .map(existingExamen -> {
                if (examen.getNombre() != null) {
                    existingExamen.setNombre(examen.getNombre());
                }

                return existingExamen;
            })
            .map(examenRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findAll() {
        log.debug("Request to get all Examen");
        return examenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Examen> findOne(Integer id) {
        log.debug("Request to get Examen : {}", id);
        Optional<Examen> opExamen = examenRepository.findById(id);
        if (opExamen.isPresent()) {
            if (opExamen.get().getMomentoCierre().before(new Date())) {
                opExamen.get().setCerrado(Boolean.TRUE);
            }
        }
        return examenRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Examen : {}", id);
        examenRepository.deleteById(id);
    }
}
