package cgmaig.llave.weather.service;

import cgmaig.llave.weather.dto.MunicipioConClimaDto;
import cgmaig.llave.weather.dto.MunicipioDto;
import cgmaig.llave.weather.dto.WeatherResponse;
import cgmaig.llave.weather.enitty.MunicipioClima;
import cgmaig.llave.weather.repository.MunicipioClimaRepository;
import cgmaig.llave.weather.repository.MunicipioRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WeatherServiceImpl implements WeatherService {

  @Autowired
  MunicipioRepository municipioRepository;

  @Autowired
  MunicipioService municipioService;

  @Autowired
  MunicipioClimaRepository municipioClimaRepository;

  @Autowired
  ObjectMapper objectMapper;

  @Value("${openweathermap.api.key}")
  private String apiKey;

  private final RestTemplate restTemplate = new RestTemplate();

  @Override
  public WeatherResponse getWeatherByCity(String city) {
    String url = String.format(
            "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s",
            city, apiKey
    );
    WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
    String json = restTemplate.getForObject(url, String.class);
    System.out.println(json);
    System.out.println(json);
    if (response == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el clima");
    }
    return response;
  }

  @Override
  public WeatherResponse getWeatherByGeo(String latitud, String longitud) {
    String url = String.format(
            "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=%s",
            latitud, longitud, apiKey
    );
    WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
    String json = restTemplate.getForObject(url, String.class);
    //System.out.println(json);
    if (response == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el clima");
    }
    return response;
  }


  public String getWeatherJsonByGeo(String latitud, String longitud) {
    String url = String.format(
            "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=%s",
            latitud, longitud, apiKey
    );
    return restTemplate.getForObject(url, String.class);
  }

  public MunicipioConClimaDto obtenerMunicipioConClima(double lat, double lon) {
    // 1. Buscar municipio
    MunicipioDto municipio = municipioService.buscarMunicipioPorCoordenadas(lat, lon);

    // 2. Buscar clima previamente guardado
    String climaJsonString = municipioClimaRepository.findById(municipio.getId())
            .map(MunicipioClima::getClimaJson)
            .orElse("{}");

    // 3. Convertir String a JsonNode
    JsonNode climaJson;
    try {
      climaJson = objectMapper.readTree(climaJsonString);
    } catch (Exception e) {
      climaJson = objectMapper.createObjectNode(); // nodo vacío si hay error
    }

    // 3. Devolver DTO
    return new MunicipioConClimaDto(municipio.getId(), municipio.getNombre(), climaJson);
  }
}
