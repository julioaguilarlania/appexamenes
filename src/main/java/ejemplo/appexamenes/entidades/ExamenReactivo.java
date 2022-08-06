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
@Table(name = "examen_reactivo")
public class ExamenReactivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examen_reactivo")
    private Integer idExamenReactivo;
    @Column(name = "posicion")
    private Integer posicion;
    @Column(name = "id_examen")
    @Basic(optional = false)
    private Integer idExamen;
    @Column(name = "id_reactivo")
    @Basic(optional = false)
    private Integer idReactivo;

    public ExamenReactivo() {
    }

    public ExamenReactivo(Integer idExamenReactivo) {
        this.idExamenReactivo = idExamenReactivo;
    }

    public ExamenReactivo(Integer idExamen, Integer idReactivo) {
        this.idExamen = idExamen;
        this.idReactivo = idReactivo;
    }
    
    

    public Integer getIdExamenReactivo() {
        return idExamenReactivo;
    }

    public void setIdExamenReactivo(Integer idExamenReactivo) {
        this.idExamenReactivo = idExamenReactivo;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
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
        hash += (idExamenReactivo != null ? idExamenReactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenReactivo)) {
            return false;
        }
        ExamenReactivo other = (ExamenReactivo) object;
        if ((this.idExamenReactivo == null && other.idExamenReactivo != null) || (this.idExamenReactivo != null && !this.idExamenReactivo.equals(other.idExamenReactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejemplo.appexamenes.entidades.ExamenReactivo[ idExamenReactivo=" + idExamenReactivo + " ]";
    }
    
}
