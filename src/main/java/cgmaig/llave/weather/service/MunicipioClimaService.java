package cgmaig.llave.weather.service;

import cgmaig.llave.weather.dto.MunicipioDto;

import java.util.List;

public interface MunicipioClimaService {
  void guardarClima(Long municipioId, String municipioNombre, String climaJson);
}
