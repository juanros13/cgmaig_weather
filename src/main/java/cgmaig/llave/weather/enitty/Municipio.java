package cgmaig.llave.weather.enitty;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Data
@Table(name = "municipios_tabasco")
public class Municipio {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String nombre;

  private String latitud;

  private String longitud;

  @Column(columnDefinition = "geometry")
  private Geometry geom;
}

