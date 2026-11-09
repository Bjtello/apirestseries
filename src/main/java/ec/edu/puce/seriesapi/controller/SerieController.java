package ec.edu.puce.seriesapi.controller;

import ec.edu.puce.seriesapi.models.Serie;
import ec.edu.puce.seriesapi.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
@CrossOrigin(origins = "*")
public class SerieController {

    @Autowired
    private SerieService serieService;

    // GET /api/series
    @GetMapping
    public ResponseEntity<List<Serie>> listarTodas() {
        return ResponseEntity.ok(serieService.listarTodas());
    }

    // GET /api/series/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Serie> obtenerPorId(@PathVariable Long id) {
        return serieService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/series
    @PostMapping
    public ResponseEntity<Serie> crearSerie(@RequestBody Serie serie) {
        Serie nuevaSerie = serieService.crearSerie(serie);
        return new ResponseEntity<>(nuevaSerie, HttpStatus.CREATED);
    }

    // PUT /api/series/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Serie> actualizarSerie(@PathVariable Long id, @RequestBody Serie serie) {
        return serieService.actualizarSerie(id, serie)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/series/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSerie(@PathVariable Long id) {
        if (serieService.eliminarSerie(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}