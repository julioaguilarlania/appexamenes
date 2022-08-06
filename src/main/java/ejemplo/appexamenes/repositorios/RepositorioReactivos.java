package ejemplo.appexamenes.repositorios;

import java.util.List;
import ejemplo.appexamenes.entidades.Reactivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MariadeLourdesHernan
 */
@Repository
public interface RepositorioReactivos extends JpaRepository<Reactivo, Integer>{
    
    @Query(value = "SELECT * FROM reactivo WHERE id_experiencia_educativa = :idee", nativeQuery = true)
    List<Reactivo> obtenerPorExperienciaEducativa(@Param("idee")int idEe);
}
