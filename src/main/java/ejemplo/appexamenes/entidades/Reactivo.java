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
@Table(name = "reactivo")
public class Reactivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reactivo")
    private Integer idReactivo;
    @Basic(optional = false)
    @Column(name = "texto")
    private String texto;
    @Basic(optional = false)
    @Column(name = "opcion_1")
    private String opcion1;
    @Basic(optional = false)
    @Column(name = "opcion_2")
    private String opcion2;
    @Column(name = "opcion_3")
    private String opcion3;
    @Column(name = "opcion_4")
    private String opcion4;
    @Basic(optional = false)
    @Column(name = "opcion_correcta")
    private Integer opcionCorrecta;
    @JoinColumn(name = "id_experiencia_educativa", referencedColumnName = "id_experiencia_educativa")
    @ManyToOne
    private ExperienciaEducativa idExperienciaEducativa;

    public Reactivo() {
    }

    public Reactivo(Integer idReactivo) {
        this.idReactivo = idReactivo;
    }

    public Reactivo(Integer idReactivo, String texto, String opcion1, String opcion2, int opcionCorrecta) {
        this.idReactivo = idReactivo;
        this.texto = texto;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcionCorrecta = opcionCorrecta;
    }

    public Integer getIdReactivo() {
        return idReactivo;
    }

    public void setIdReactivo(Integer idReactivo) {
        this.idReactivo = idReactivo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public Integer getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public void setOpcionCorrecta(Integer opcionCorrecta) {
        this.opcionCorrecta = opcionCorrecta;
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
        hash += (idReactivo != null ? idReactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reactivo)) {
            return false;
        }
        Reactivo other = (Reactivo) object;
        if ((this.idReactivo == null && other.idReactivo != null) || (this.idReactivo != null && !this.idReactivo.equals(other.idReactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejemplo.appexamenes.entidades.Reactivo[ idReactivo=" + idReactivo + " ]";
    }
    
}
