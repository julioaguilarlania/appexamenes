package ejemplo.appexamenes.servicios.impl;

import ejemplo.appexamenes.entidades.Reactivo;
import ejemplo.appexamenes.repositorios.RepositorioReactivos;
import ejemplo.appexamenes.servicios.ServicioReactivos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MariadeLourdesHernan
 */
@Service
@Transactional
public class ServicioReactivosImpl implements ServicioReactivos {
    
    RepositorioReactivos repoReactivos;

    public ServicioReactivosImpl(RepositorioReactivos repoReactivos) {
        this.repoReactivos = repoReactivos;
    }

    @Override
    public Reactivo guardar(Reactivo reactivo) {
        return repoReactivos.save(reactivo);
    }

    @Override
    public Reactivo actualizar(Reactivo reactivo) {
        return repoReactivos.save(reactivo);
    }

    @Override
    public void borrar(Integer idReactivo) {
        repoReactivos.deleteById(idReactivo);
    }
    
    
}
