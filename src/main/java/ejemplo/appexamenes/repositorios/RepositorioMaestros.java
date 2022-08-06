package ejemplo.appexamenes.repositorios;

import ejemplo.appexamenes.entidades.Maestro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Maestro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RepositorioMaestros extends JpaRepository<Maestro, Integer> {}
