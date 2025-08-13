package cgmaig.llave.weather.service;

import cgmaig.llave.weather.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WeatherServiceImpl implements WeatherService {

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
}
