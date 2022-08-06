package ejemplo.appexamenes.dto;

import java.util.List;

/**
 *
 * @author MariadeLourdesHernan
 */
public class SeleccionReactivos {

    Integer idExamen;
    List<Integer> idsReactivos;

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public List<Integer> getIdsReactivos() {
        return idsReactivos;
    }

    public void setIdsReactivos(List<Integer> idsReactivos) {
        this.idsReactivos = idsReactivos;
    }
}
