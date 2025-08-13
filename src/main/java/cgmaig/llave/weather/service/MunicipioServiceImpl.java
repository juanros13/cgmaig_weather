package cgmaig.llave.weather.service;

import cgmaig.llave.weather.dto.MunicipioDto;
import cgmaig.llave.weather.enitty.Municipio;
import cgmaig.llave.weather.enitty.mapper.MunicipioMapper;
import cgmaig.llave.weather.repository.MunicipioRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioServiceImpl implements MunicipioService {

  @Autowired
  MunicipioRepository municipioRepository;

  private final GeometryFactory geometryFactory = new GeometryFactory();

  private final MunicipioMapper municipioMapper = new MunicipioMapper();
  @Override
  public List<MunicipioDto> getAllMunicipios() {
    List<Municipio> municipios = municipioRepository.findAll();
    return municipios.stream()
            .map(m -> new MunicipioDto(m.getId(), m.getNombre(), m.getLatitud(), m.getLongitud()))
            .collect(Collectors.toList());
  }

  public MunicipioDto buscarMunicipioPorCoordenadas(double lat, double lon) {
    Point point = geometryFactory.createPoint(new Coordinate(lon, lat)); // ojo: lon primero
    point.setSRID(4326);  // <- Muy importante
    return municipioMapper.convertToDto(municipioRepository.findByLocation(point));

  }
}
