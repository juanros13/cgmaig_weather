package cgmaig.llave.weather.service;

import cgmaig.llave.weather.dto.MunicipioDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface MunicipioClimaService {
  void guardarClima(Long municipioId, String municipioNombre, JsonNode climaJson) throws JsonProcessingException;
}
