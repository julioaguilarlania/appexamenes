package ejemplo.appexamenes.servicios;

import ejemplo.appexamenes.entidades.Reactivo;

/**
 *
 * @author MariadeLourdesHernan
 */
public interface ServicioReactivos {
    
    public Reactivo guardar(Reactivo reactivo);
    
    public Reactivo actualizar(Reactivo reactivo);
    
    public void borrar(Integer idReactivo);

}
