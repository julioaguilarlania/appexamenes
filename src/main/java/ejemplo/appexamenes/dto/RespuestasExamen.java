package ejemplo.appexamenes.dto;

import java.util.List;
import ejemplo.appexamenes.entidades.RespuestaReactivo;

/**
 *
 * @author jaguilar
 */
public class RespuestasExamen {

    Integer idExamen;
    Integer idAlumno;
    List<RespuestaReactivo> respuestas;

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public List<RespuestaReactivo> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaReactivo> respuestas) {
        this.respuestas = respuestas;
    }
}
