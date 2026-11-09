package ec.edu.puce.seriesapi.repository;

import ec.edu.puce.seriesapi.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
}