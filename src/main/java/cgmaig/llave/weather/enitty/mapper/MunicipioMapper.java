package cgmaig.llave.weather.enitty.mapper;

import cgmaig.llave.weather.dto.MunicipioDto;
import cgmaig.llave.weather.enitty.Municipio;

// create class CatCommitteeMaper here
public class MunicipioMapper extends BaseMapper<Municipio, MunicipioDto> {
  @Override
  public Municipio convertToEntity(MunicipioDto dto, Object... args) {
    Municipio catCommitteeEntity = new Municipio();
    if (dto != null) {
      catCommitteeEntity.setId(dto.getId());
      catCommitteeEntity.setNombre(dto.getNombre());
      catCommitteeEntity.setLatitud(dto.getLatitud());
      catCommitteeEntity.setLongitud(dto.getLongitud());
    }
    return catCommitteeEntity;
  }

  @Override
  public MunicipioDto convertToDto(Municipio entity, Object... args) {
    MunicipioDto catCommitteeDto = new MunicipioDto();
    if (entity != null) {
      catCommitteeDto.setId(entity.getId());
      catCommitteeDto.setNombre(entity.getNombre());
      catCommitteeDto.setLatitud(entity.getLatitud());
      catCommitteeDto.setLongitud(entity.getLongitud());
    }
    return catCommitteeDto;
  }
}
