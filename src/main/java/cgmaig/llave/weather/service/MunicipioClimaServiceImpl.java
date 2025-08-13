package cgmaig.llave.weather.service;

import cgmaig.llave.weather.enitty.MunicipioClima;
import cgmaig.llave.weather.repository.MunicipioClimaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MunicipioClimaServiceImpl implements MunicipioClimaService{

  @Autowired
  MunicipioClimaRepository repository;

  @Autowired
  ObjectMapper objectMapper;


  @Transactional
  public void guardarClima(Long municipioId, String municipioNombre, JsonNode climaJson) throws JsonProcessingException {
    // Elimina si existe
    if(repository.existsById(municipioId)){
      repository.deleteById(municipioId);
    }
    // Inserta nuevo
    MunicipioClima registro = repository.findById(municipioId).orElse(new MunicipioClima());
    registro.setMunicipioId(municipioId);
    registro.setMunicipio(municipioNombre);
    String jsonString = objectMapper.writeValueAsString(climaJson);
    registro.setClimaJson(jsonString);
    registro.setFechaActualizacion(LocalDateTime.now());

    repository.save(registro); // si ya existía hace update, si no, insert

  }
}
