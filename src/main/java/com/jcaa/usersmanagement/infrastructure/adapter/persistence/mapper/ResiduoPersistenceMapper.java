package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import com.jcaa.usersmanagement.domain.valueobject.ResiduoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ResiduoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.ResiduoEntity;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResiduoPersistenceMapper {

  public ResiduoPersistenceDto fromModelToDto(final ResiduoModel residuo) {
    return new ResiduoPersistenceDto(
        residuo.getId().value(),
        residuo.getIdProductor(),
        residuo.getTipoResiduo(),
        residuo.isPeligroso(),
        residuo.getPesoKg().toPlainString(),
        residuo.getFechaGeneracion().toString());
  }

  public ResiduoEntity fromResultSetToEntity(final ResultSet rs) throws SQLException {
    return new ResiduoEntity(
        rs.getString("id"),
        rs.getString("id_productor"),
        rs.getString("tipo_residuo"),
        rs.getBoolean("peligroso"),
        rs.getString("peso_kg"),
        rs.getString("fecha_generacion"));
  }

  public ResiduoModel fromEntityToModel(final ResiduoEntity entity) {
    return new ResiduoModel(
        new ResiduoId(entity.id()),
        entity.idProductor(),
        entity.tipoResiduo(),
        entity.peligroso(),
        new BigDecimal(entity.pesoKg()),
        LocalDate.parse(entity.fechaGeneracion()));
  }

  public ResiduoModel fromResultSetToModel(final ResultSet rs) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(rs));
  }

  public List<ResiduoModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
    final List<ResiduoModel> list = new ArrayList<>();
    while (rs.next()) {
      list.add(fromResultSetToModel(rs));
    }
    return list;
  }
}
