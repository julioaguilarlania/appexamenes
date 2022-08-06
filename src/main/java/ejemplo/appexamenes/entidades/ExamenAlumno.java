package ejemplo.appexamenes.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author MariadeLourdesHernan
 */
@Entity
@Table(name = "examen_alumno")
public class ExamenAlumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examen_alumno")
    private Integer idExamenAlumno;
    @Column(name = "momento_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date momentoInicio;
    @Column(name = "momento_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date momentoFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "calificacion")
    private BigDecimal calificacion;
    @Basic(optional = false)
    @Column(name = "id_alumno")
    private Integer idAlumno;
    @Basic(optional = false)
    @Column(name = "id_examen")
    private Integer idExamen;
    
    @Transient
    private Examen examen;

    @Transient
    private Alumno alumno;

    public ExamenAlumno() {
    }

    public ExamenAlumno(Integer idExamenAlumno) {
        this.idExamenAlumno = idExamenAlumno;
    }

    public Integer getIdExamenAlumno() {
        return idExamenAlumno;
    }

    public void setIdExamenAlumno(Integer idExamenAlumno) {
        this.idExamenAlumno = idExamenAlumno;
    }

    public Date getMomentoInicio() {
        return momentoInicio;
    }

    public void setMomentoInicio(Date momentoInicio) {
        this.momentoInicio = momentoInicio;
    }

    public Date getMomentoFin() {
        return momentoFin;
    }

    public void setMomentoFin(Date momentoFin) {
        this.momentoFin = momentoFin;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenAlumno != null ? idExamenAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenAlumno)) {
            return false;
        }
        ExamenAlumno other = (ExamenAlumno) object;
        if ((this.idExamenAlumno == null && other.idExamenAlumno != null) || (this.idExamenAlumno != null && !this.idExamenAlumno.equals(other.idExamenAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejemplo.appexamenes.entidades.ExamenAlumno[ idExamenAlumno=" + idExamenAlumno + " ]";
    }

    
}
