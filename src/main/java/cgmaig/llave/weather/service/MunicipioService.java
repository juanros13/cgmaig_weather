package cgmaig.llave.weather.service;

import cgmaig.llave.weather.dto.MunicipioDto;
import cgmaig.llave.weather.enitty.Municipio;

import java.util.List;

public interface MunicipioService {
  List<MunicipioDto> getAllMunicipios();
  MunicipioDto buscarMunicipioPorCoordenadas(double lat, double lon);
}
