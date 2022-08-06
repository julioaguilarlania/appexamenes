package ejemplo.appexamenes.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import ejemplo.appexamenes.dto.SeleccionReactivos;
import ejemplo.appexamenes.entidades.Reactivo;
import ejemplo.appexamenes.repositorios.RepositorioReactivos;
import ejemplo.appexamenes.servicios.ServicioExamenesReactivos;
import ejemplo.appexamenes.servicios.ServicioReactivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author MariadeLourdesHernan
 */
@RestController
@RequestMapping("/api")
@CrossOrigin()
public class ControladorReactivos {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControladorReactivos.class);
    
    RepositorioReactivos repoReactivos;

    ServicioReactivos servReactivos;
    
    ServicioExamenesReactivos servExamenesReactivos;

    public ControladorReactivos(RepositorioReactivos repoReactivos, ServicioReactivos servReactivos, ServicioExamenesReactivos servExamenesReactivos) {
        this.repoReactivos = repoReactivos;
        this.servReactivos = servReactivos;
        this.servExamenesReactivos = servExamenesReactivos;
    }
    @GetMapping(value = "/reactivo")
    public List<Reactivo> getTodos() {
        return repoReactivos.findAll();
    }
    
    @GetMapping(value = "/reactivo", params = {"idExamen"})
    public List<Reactivo> getPorExamen(@RequestParam("idExamen") Integer idExamen) {
        SeleccionReactivos sel = servExamenesReactivos.findByIdExamen(idExamen);
        return repoReactivos.findAllById(sel.getIdsReactivos());
    }

    @GetMapping("/reactivo/{id}")
    public ResponseEntity<Reactivo> getPorId(@PathVariable("id") Integer id) {
        Optional<Reactivo> opR = repoReactivos.findById(id);
        if (opR.isPresent()) {
            return ResponseEntity.ok(opR.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/reactivo")
    public ResponseEntity<Reactivo> guardarReactivo(@RequestBody Reactivo re) {
        try {
            Reactivo reNuevo = servReactivos.guardar(re);
            URI uriNuevoReactivo = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(reNuevo.getIdReactivo())
                    .toUri();
            return ResponseEntity.created(uriNuevoReactivo)
                    .body(reNuevo);
        }
        catch(Exception ex) {
            LOGGER.warn("Error al insertar reactivo", ex);
            return ResponseEntity.badRequest()
                    .header("ERROR", ex.getMessage())
                    .build();
        }
    }
    
    @PutMapping("/reactivo/{id}")
    public ResponseEntity<Reactivo> updateReactivo(@PathVariable("id") final Integer id, 
            @RequestBody Reactivo reactivo)
        throws URISyntaxException {

        Reactivo result = servReactivos.actualizar(reactivo);
        return ResponseEntity
            .ok()
            .body(result);
    }
    
     @DeleteMapping("/reactivo/{id}")
    public ResponseEntity<Void> deleteReactivo(@PathVariable Integer id) {
        servReactivos.borrar(id);
        return ResponseEntity
            .noContent()
            .build();
    }

}
