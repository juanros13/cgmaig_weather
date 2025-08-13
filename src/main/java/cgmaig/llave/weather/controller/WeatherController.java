package cgmaig.llave.weather.controller;

import cgmaig.llave.weather.dto.MunicipioConClimaDto;
import cgmaig.llave.weather.dto.MunicipioDto;
import cgmaig.llave.weather.dto.WeatherResponse;
import cgmaig.llave.weather.service.MunicipioClimaService;
import cgmaig.llave.weather.service.MunicipioService;
import cgmaig.llave.weather.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

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

  @Autowired
  ObjectMapper objectMapper;



  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping("/{city}")
  public WeatherResponse getWeather(@PathVariable String city) {
    return weatherService.getWeatherByCity(city);
  }


//  @GetMapping("/municipios-con-clima")
//  public List<MunicipioConClimaDto> municipiosConClima() {
//    List<MunicipioDto> municipios = municipioService.getAllMunicipios();
//
//    // Para cada municipio, consultar el clima y poner en un Map <municipioNombre, WeatherResponse>
//    return municipios.stream()
//            .map(m -> {
//              Object clima = weatherService.getWeatherByGeo(m.getLatitud(), m.getLongitud());
//              return new MunicipioConClimaDto(m.getId(), m.getNombre(), clima);
//            })
//            .collect(Collectors.toList());
//  }


  @GetMapping("/actualizar-climas")
  public List<MunicipioConClimaDto> actualizarClimas() {
    List<MunicipioDto> municipios = municipioService.getAllMunicipios();

    return municipios.stream()
            .map(m -> {
              String climaJsonString = weatherService.getWeatherJsonByGeo(m.getLatitud(), m.getLongitud());
              // Guarda o actualiza en la tabla
              JsonNode climaJson;
              try {
                climaJson = objectMapper.readTree(climaJsonString);
              } catch (Exception e) {
                climaJson = objectMapper.createObjectNode(); // nodo vacío si hay error
              }
              try {
                municipioClimaService.guardarClima(m.getId(), m.getNombre(), climaJson);
              } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
              }
              return new MunicipioConClimaDto(m.getId(), m.getNombre(), climaJson);
            })
            .toList();
  }

  @GetMapping("/municipio-clima")
  public MunicipioConClimaDto obtenerMunicipioClima(
          @RequestParam double lat,
          @RequestParam double lon) {
    return weatherService.obtenerMunicipioConClima(lat, lon);
  }
}
