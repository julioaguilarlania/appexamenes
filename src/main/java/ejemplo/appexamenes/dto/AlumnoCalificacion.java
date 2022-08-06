package ejemplo.appexamenes.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MariadeLourdesHernan
 */
@Entity
public class AlumnoCalificacion implements Serializable {

    @Id
    private Integer idAlumno;

    public AlumnoCalificacion(Integer idAlumno, String nombres, String apellidos, BigDecimal calificacion, Date momentoFin) {
        this.idAlumno = idAlumno;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.calificacion = calificacion;
        this.momentoFin = momentoFin;
    }
    private String nombres;
    private String apellidos;
    private BigDecimal calificacion;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date momentoFin;

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    public Date getMomentoFin() {
        return momentoFin;
    }

    public void setMomentoFin(Date momentoFin) {
        this.momentoFin = momentoFin;
    }
}
