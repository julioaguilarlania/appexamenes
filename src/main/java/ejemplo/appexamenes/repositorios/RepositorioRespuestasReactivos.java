package ejemplo.appexamenes.repositorios;

import ejemplo.appexamenes.entidades.RespuestaReactivo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RespuestaReactivo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RepositorioRespuestasReactivos extends JpaRepository<RespuestaReactivo, Integer> {}
