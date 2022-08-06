package ejemplo.appexamenes.repositorios;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.entidades.Usuario;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

}
