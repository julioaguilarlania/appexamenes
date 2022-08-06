package ejemplo.appexamenes.repositorios;

import ejemplo.appexamenes.entidades.ExperienciaEducativa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExperienciaEducativa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RepositorioExperienciasEducativas extends JpaRepository<ExperienciaEducativa, Integer> {}
