package cgmaig.llave.weather.controller;

import cgmaig.llave.weather.dto.MunicipioDto;
import cgmaig.llave.weather.enitty.Municipio;
import cgmaig.llave.weather.service.MunicipioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {
  private final MunicipioService municipioService;

  public MunicipioController(MunicipioService municipioService) {
    this.municipioService = municipioService;
  }

  @GetMapping
  public List<MunicipioDto> listarMunicipios() {
    return municipioService.getAllMunicipios();
  }


  @GetMapping("/municipio-por-coordenadas")
  public MunicipioDto obtenerMunicipio(
          @RequestParam double lat,
          @RequestParam double lon) {
    return municipioService.buscarMunicipioPorCoordenadas(lat, lon);
  }
}
