package cgmaig.llave.weather.enitty;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "municipios_clima")
public class MunicipioClima {

  @Id
  private Long municipioId;

  private String municipio;

  @Column(columnDefinition = "jsonb")
  @JdbcTypeCode(SqlTypes.JSON)
  private String climaJson;

  private LocalDateTime fechaActualizacion = LocalDateTime.now();

  // Getters y setters
}
