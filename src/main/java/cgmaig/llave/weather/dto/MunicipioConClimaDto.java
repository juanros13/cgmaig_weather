package cgmaig.llave.weather.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class MunicipioConClimaDto {
  private Long municipioId;
  private String municipio;
  private JsonNode data; // Puedes usar WeatherResponse o Object para flexibilidad

  public MunicipioConClimaDto() {}

  public MunicipioConClimaDto(Long municipioId, String municipio, JsonNode data) {
    this.municipioId = municipioId;
    this.municipio = municipio;
    this.data = data;
  }

  // Getters y setters
  public Long getMunicipioId() { return municipioId; }
  public void setMunicipioId(Long municipioId) { this.municipioId = municipioId; }

  public String getMunicipio() { return municipio; }
  public void setMunicipio(String municipio) { this.municipio = municipio; }

  public JsonNode getData() { return data; }
  public void setData(JsonNode data) { this.data = data; }
}
