package cgmaig.llave.weather.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MunicipioDto {
  private Long id;
  private String nombre;

  private String latitud;

  private String longitud;


  public MunicipioDto(Long id, String nombre, String latitud, String longitud) {
    this.id = id;
    this.nombre = nombre;
    this.latitud = latitud;
    this.longitud = longitud;
  }

}
