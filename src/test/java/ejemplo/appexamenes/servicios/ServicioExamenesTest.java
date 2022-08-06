package ejemplo.appexamenes.servicios;

import ejemplo.appexamenes.entidades.Examen;
import ejemplo.appexamenes.entidades.ExperienciaEducativa;
import ejemplo.appexamenes.repositorios.RepositorioExamenes;
import ejemplo.appexamenes.servicios.impl.ServicioExamenesImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author jaguilar
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ServicioExamenesTest {
    
    
    
    @Mock
    RepositorioExamenes repoExamenes;
    
    @InjectMocks
    ServicioExamenesImpl servExamenes;
    
    SimpleDateFormat sdf;
    
    @BeforeAll
    public void prepararServicio() {
        //servExamenes = new ServicioExamenesImpl(repoExamenes);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }
    
    @Test
    public void testGuardarExamen() {
        Examen ex = new Examen();
        ex.setIdExperienciaEducativa(new ExperienciaEducativa(1));
        ex.setNombre("EX1");
        ex.setMomentoApertura(leerFechaDesdeCadena("2022-08-15 10:00"));
        ex.setMomentoCierre(leerFechaDesdeCadena("2022-08-15 15:00"));
        servExamenes.save(ex);
        //Assertions.assertNotNull(ex.getIdExamen());
        Assertions.assertFalse(ex.getCerrado());
    }

    @Test
    public void testGuardarExamenConFechasEquivocadas() {
        Examen ex = new Examen();
        ex.setIdExperienciaEducativa(new ExperienciaEducativa(1));
        ex.setNombre("EX1");
        ex.setMomentoApertura(leerFechaDesdeCadena("2022-08-15 10:00"));
        ex.setMomentoCierre(leerFechaDesdeCadena("2022-08-14 15:00"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> servExamenes.save(ex));
    }

    @Test
    public void testGuardarExamenSinNombre() {
        Examen ex = new Examen();
        ex.setIdExperienciaEducativa(new ExperienciaEducativa(1));
        ex.setMomentoApertura(leerFechaDesdeCadena("2022-08-15 10:00"));
        ex.setMomentoCierre(leerFechaDesdeCadena("2022-08-15 15:00"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> servExamenes.save(ex));
    }
    
    @Test
    public void testGetExamenes() {
        Examen resultado = new Examen(1);
        resultado.setMomentoCierre(leerFechaDesdeCadena("2022-07-01 14:00"));
        resultado.setCerrado(Boolean.FALSE);
        
        when(repoExamenes.findById(any(Integer.class))).thenReturn(Optional.of(resultado));
        
        Optional<Examen> opExamen = servExamenes.findOne(1);
        Assertions.assertTrue(opExamen.isPresent());
        Assertions.assertTrue(opExamen.get().getCerrado());
    }
            
    
    Date leerFechaDesdeCadena(String cadena) {
        try {
            return sdf.parse(cadena);
        }
        catch (ParseException pe) {
            System.out.println(".");
            return new Date();
        }
    }
}
