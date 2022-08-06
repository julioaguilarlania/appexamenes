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
@Table(name = "experiencia_educativa")
public class ExperienciaEducativa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_experiencia_educativa")
    private Integer idExperienciaEducativa;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "nrc")
    private int nrc;
    @JoinColumn(name = "id_maestro", referencedColumnName = "id_maestro")
    @ManyToOne
    private Maestro idMaestro;

    public ExperienciaEducativa() {
    }

    public ExperienciaEducativa(Integer idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public ExperienciaEducativa(Integer idExperienciaEducativa, String nombre, int nrc) {
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.nombre = nombre;
        this.nrc = nrc;
    }

    public Integer getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(Integer idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public Maestro getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Maestro idMaestro) {
        this.idMaestro = idMaestro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExperienciaEducativa != null ? idExperienciaEducativa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExperienciaEducativa)) {
            return false;
        }
        ExperienciaEducativa other = (ExperienciaEducativa) object;
        if ((this.idExperienciaEducativa == null && other.idExperienciaEducativa != null) || (this.idExperienciaEducativa != null && !this.idExperienciaEducativa.equals(other.idExperienciaEducativa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejemplo.appexamenes.entidades.ExperienciaEducativa[ idExperienciaEducativa=" + idExperienciaEducativa + " ]";
    }
    
}
