package ejemplo.appexamenes.test;

import ejemplo.appexamenes.entidades.Examen;
import ejemplo.appexamenes.entidades.ExperienciaEducativa;
import ejemplo.appexamenes.rest.ControladorExamenes;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author jaguilar
 */
@SpringBootTest
public class ControladorExamenesTest {

    @Autowired
    ControladorExamenes ctrlExamenes;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Test
    public void validarGuardado() {
        Examen ex = new Examen();
        ex.setNombre("EXAMEN1");
        ex.setIdExperienciaEducativa(new ExperienciaEducativa(1));
        ex.setMomentoApertura(leerFechaDesdeCadena("2022-08-15 10:00"));
        ex.setMomentoCierre(leerFechaDesdeCadena("2022-08-15 15:00"));
        
        try {
            Examen resultado = ctrlExamenes.createExamen(ex).getBody();
            Assertions.assertNotNull(resultado);
            Assertions.assertNotNull(resultado.getIdExamen());
        } catch (URISyntaxException ex1) {
            Logger.getLogger(ControladorExamenesTest.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

    @Test
    public void validarGuardadoIncompleto() {
        Examen ex = new Examen();
        ex.setNombre("EXAMEN1");
        ex.setMomentoApertura(leerFechaDesdeCadena("2022-08-15 10:00"));
        ex.setMomentoCierre(leerFechaDesdeCadena("2022-08-15 15:00"));
        Assertions.assertThrows(DataAccessException.class, () -> ctrlExamenes.createExamen(ex));
    }

    Date leerFechaDesdeCadena(String cadena) {
        try {
            return sdf.parse(cadena);
        } catch (ParseException pe) {
            System.out.println(".");
            return new Date();
        }
    }

}
