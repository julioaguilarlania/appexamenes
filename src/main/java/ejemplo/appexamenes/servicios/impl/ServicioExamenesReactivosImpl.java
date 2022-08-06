package ejemplo.appexamenes.servicios.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import ejemplo.appexamenes.dto.SeleccionReactivos;
import ejemplo.appexamenes.entidades.ExamenReactivo;
import ejemplo.appexamenes.repositorios.RepositorioExamenesReactivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ejemplo.appexamenes.servicios.ServicioExamenesReactivos;

/**
 * Service Implementation for managing {@link ExamenReactivo}.
 */
@Service
@Transactional
public class ServicioExamenesReactivosImpl implements ServicioExamenesReactivos {

    private final Logger log = LoggerFactory.getLogger(ServicioExamenesReactivosImpl.class);

    private final RepositorioExamenesReactivos examenReactivoRepository;

    public ServicioExamenesReactivosImpl(RepositorioExamenesReactivos examenReactivoRepository) {
        this.examenReactivoRepository = examenReactivoRepository;
    }

    @Override
    public void save(SeleccionReactivos seleccion) {
        log.debug("Request to save SeleccionReactivos : {}", seleccion);
        List<ExamenReactivo> ers = new ArrayList<>();
        Set<Integer> idsUnicos = new HashSet<>();
        idsUnicos.addAll(seleccion.getIdsReactivos());
        for (Integer idReactivo : idsUnicos) {
            ExamenReactivo er = new ExamenReactivo(seleccion.getIdExamen(), idReactivo);
            ers.add(er);
        }
        examenReactivoRepository.borrarPorExamen(seleccion.getIdExamen());
        examenReactivoRepository.saveAll(ers);
    }

    @Override
    public ExamenReactivo update(ExamenReactivo examenReactivo) {
        log.debug("Request to save ExamenReactivo : {}", examenReactivo);
        return examenReactivoRepository.save(examenReactivo);
    }

    @Override
    public Optional<ExamenReactivo> partialUpdate(ExamenReactivo examenReactivo) {
        log.debug("Request to partially update ExamenReactivo : {}", examenReactivo);

        return examenReactivoRepository
            .findById(examenReactivo.getIdExamenReactivo())
            .map(existingExamenReactivo -> {
                if (examenReactivo.getPosicion() != null) {
                    existingExamenReactivo.setPosicion(examenReactivo.getPosicion());
                }

                return existingExamenReactivo;
            })
            .map(examenReactivoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public SeleccionReactivos findByIdExamen(Integer idExamen) {
        log.debug("Request to get Reactivos por exam");
        SeleccionReactivos sel = new SeleccionReactivos();
        Collection<ExamenReactivo> ers = examenReactivoRepository.findByIdExamen(idExamen);
        sel.setIdExamen(idExamen);
        sel.setIdsReactivos(ers.stream().map(er -> er.getIdReactivo()).toList());
        return sel;
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete ExamenReactivo : {}", id);
        examenReactivoRepository.deleteById(id);
    }
}
