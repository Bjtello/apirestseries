package ec.edu.puce.seriesapi.service;

import ec.edu.puce.seriesapi.models.Serie;
import ec.edu.puce.seriesapi.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<Serie> listarTodas() {
        return serieRepository.findAll();
    }

    public Optional<Serie> obtenerPorId(Long id) {
        return serieRepository.findById(id);
    }

    public Serie crearSerie(Serie serie) {
        serie.setId(null);
        return serieRepository.save(serie);
    }

    public Optional<Serie> actualizarSerie(Long id, Serie serieActualizada) {
        return serieRepository.findById(id).map(serieExistente -> {
            serieExistente.setTitulo(serieActualizada.getTitulo());
            serieExistente.setPlataforma(serieActualizada.getPlataforma());
            serieExistente.setCalificacion(serieActualizada.getCalificacion());
            return serieRepository.save(serieExistente);
        });
    }

    public boolean eliminarSerie(Long id) {
        if (serieRepository.existsById(id)) {
            serieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}