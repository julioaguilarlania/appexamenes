package ejemplo.appexamenes.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MariadeLourdesHernan
 */
@Entity
@Table(name = "examen")
public class Examen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examen")
    private Integer idExamen;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "momento_apertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date momentoApertura;
    @Column(name = "momento_cierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date momentoCierre;
    
    @Basic(optional = false)
    @Column(name = "cerrado")
    private Boolean cerrado;
    
    @JoinColumn(name = "id_experiencia_educativa", referencedColumnName = "id_experiencia_educativa")
    @ManyToOne(optional = false)
    private ExperienciaEducativa idExperienciaEducativa;

    public Examen() {
    }

    public Examen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Examen(Integer idExamen, String nombre) {
        this.idExamen = idExamen;
        this.nombre = nombre;
    }

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getMomentoApertura() {
        return momentoApertura;
    }

    public void setMomentoApertura(Date momentoApertura) {
        this.momentoApertura = momentoApertura;
    }

    public Date getMomentoCierre() {
        return momentoCierre;
    }

    public void setMomentoCierre(Date momentoCierre) {
        this.momentoCierre = momentoCierre;
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public ExperienciaEducativa getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(ExperienciaEducativa idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamen != null ? idExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.idExamen == null && other.idExamen != null) || (this.idExamen != null && !this.idExamen.equals(other.idExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejemplo.appexamenes.entidades.Examen[ idExamen=" + idExamen + " ]";
    }
    
}
