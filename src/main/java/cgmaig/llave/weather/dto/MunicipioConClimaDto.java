package cgmaig.llave.weather.dto;

public class MunicipioConClimaDto {
  private Long municipioId;
  private String municipio;
  private Object data; // Puedes usar WeatherResponse o Object para flexibilidad

  public MunicipioConClimaDto() {}

  public MunicipioConClimaDto(Long municipioId, String municipio, Object data) {
    this.municipioId = municipioId;
    this.municipio = municipio;
    this.data = data;
  }

  // Getters y setters
  public Long getMunicipioId() { return municipioId; }
  public void setMunicipioId(Long municipioId) { this.municipioId = municipioId; }

  public String getMunicipio() { return municipio; }
  public void setMunicipio(String municipio) { this.municipio = municipio; }

  public Object getData() { return data; }
  public void setData(Object data) { this.data = data; }
}
