package cgmaig.llave.weather.repository;

import cgmaig.llave.weather.enitty.Municipio;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
  @Query("SELECT m FROM Municipio m WHERE ST_Contains(m.geom, :point) = true")
  Municipio findByLocation(@Param("point") Point point);
}
