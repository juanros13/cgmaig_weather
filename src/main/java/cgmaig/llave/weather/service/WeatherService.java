package cgmaig.llave.weather.service;

import cgmaig.llave.weather.dto.WeatherResponse;

public interface WeatherService {
  WeatherResponse getWeatherByCity(String city);

  WeatherResponse getWeatherByGeo(String latitud,String longitud);
  String getWeatherJsonByGeo(String latitud, String longitud);
}
