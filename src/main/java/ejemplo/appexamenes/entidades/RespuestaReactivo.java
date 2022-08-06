package ejemplo.appexamenes.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author MariadeLourdesHernan
 */
@Entity
@Table(name = "respuesta_reactivo")
public class RespuestaReactivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuesta_reactivo")
    private Integer idRespuestaReactivo;
    @Basic(optional = false)
    @Column(name = "opcion")
    private Integer opcion;
    @Column(name = "correcta")
    private Boolean correcta;
    @JoinColumn(name = "id_examen_alumno", referencedColumnName = "id_examen_alumno")
    @ManyToOne(optional = false)
    private ExamenAlumno idExamenAlumno;
    
    @Column(name = "id_reactivo")
    private Integer idReactivo;

    public RespuestaReactivo() {
    }

    public RespuestaReactivo(Integer idRespuestaReactivo) {
        this.idRespuestaReactivo = idRespuestaReactivo;
    }

    public RespuestaReactivo(Integer idRespuestaReactivo, int opcion) {
        this.idRespuestaReactivo = idRespuestaReactivo;
        this.opcion = opcion;
    }

    public Integer getIdRespuestaReactivo() {
        return idRespuestaReactivo;
    }

    public void setIdRespuestaReactivo(Integer idRespuestaReactivo) {
        this.idRespuestaReactivo = idRespuestaReactivo;
    }

    public Integer getOpcion() {
        return opcion;
    }

    public void setOpcion(Integer opcion) {
        this.opcion = opcion;
    }

    public Boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Boolean correcta) {
        this.correcta = correcta;
    }

    public ExamenAlumno getIdExamenAlumno() {
        return idExamenAlumno;
    }

    public void setIdExamenAlumno(ExamenAlumno idExamenAlumno) {
        this.idExamenAlumno = idExamenAlumno;
    }


    public Integer getIdReactivo() {
        return idReactivo;
    }

    public void setIdReactivo(Integer idReactivo) {
        this.idReactivo = idReactivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaReactivo != null ? idRespuestaReactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaReactivo)) {
            return false;
        }
        RespuestaReactivo other = (RespuestaReactivo) object;
        if ((this.idRespuestaReactivo == null && other.idRespuestaReactivo != null) || (this.idRespuestaReactivo != null && !this.idRespuestaReactivo.equals(other.idRespuestaReactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejemplo.appexamenes.entidades.RespuestaReactivo[ idRespuestaReactivo=" + idRespuestaReactivo + " ]";
    }
    
}
