package cgmaig.llave.weather.controller;

import cgmaig.llave.weather.dto.MunicipioConClimaDto;
import cgmaig.llave.weather.dto.MunicipioDto;
import cgmaig.llave.weather.dto.WeatherResponse;
import cgmaig.llave.weather.service.MunicipioClimaService;
import cgmaig.llave.weather.service.MunicipioService;
import cgmaig.llave.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
  @Autowired
  WeatherService weatherService;
  @Autowired
  MunicipioService municipioService;

  @Autowired
  MunicipioClimaService municipioClimaService;


  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping("/{city}")
  public WeatherResponse getWeather(@PathVariable String city) {
    return weatherService.getWeatherByCity(city);
  }


  @GetMapping("/municipios-con-clima")
  public List<MunicipioConClimaDto> municipiosConClima() {
    List<MunicipioDto> municipios = municipioService.getAllMunicipios();

    // Para cada municipio, consultar el clima y poner en un Map <municipioNombre, WeatherResponse>
    return municipios.stream()
            .map(m -> {
              Object clima = weatherService.getWeatherByGeo(m.getLatitud(), m.getLongitud());
              return new MunicipioConClimaDto(m.getId(), m.getNombre(), clima);
            })
            .collect(Collectors.toList());
  }


  @GetMapping("/actualizar-climas")
  public List<MunicipioConClimaDto> actualizarClimas() {
    List<MunicipioDto> municipios = municipioService.getAllMunicipios();

    return municipios.stream()
            .map(m -> {
              String jsonClima = weatherService.getWeatherJsonByGeo(m.getLatitud(), m.getLongitud());
              // Guarda o actualiza en la tabla
              municipioClimaService.guardarClima(m.getId(), m.getNombre(), jsonClima);
              return new MunicipioConClimaDto(m.getId(), m.getNombre(), jsonClima);
            })
            .toList();
  }
}
