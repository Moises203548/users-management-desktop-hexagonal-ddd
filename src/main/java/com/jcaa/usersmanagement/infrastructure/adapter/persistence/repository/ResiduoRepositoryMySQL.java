package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteResiduoPort;
import com.jcaa.usersmanagement.application.port.out.GetAllResiduosPort;
import com.jcaa.usersmanagement.application.port.out.GetResiduoByIdPort;
import com.jcaa.usersmanagement.application.port.out.GetResiduosByProductorAndFechasPort;
import com.jcaa.usersmanagement.application.port.out.GetResiduosByProductorPort;
import com.jcaa.usersmanagement.application.port.out.GetResiduosByTipoPort;
import com.jcaa.usersmanagement.application.port.out.GetTotalesByProductorPort;
import com.jcaa.usersmanagement.application.port.out.SaveResiduoPort;
import com.jcaa.usersmanagement.domain.exception.ResiduoNotFoundException;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import com.jcaa.usersmanagement.domain.valueobject.ResiduoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ResiduoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.ResiduoPersistenceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class ResiduoRepositoryMySQL
    implements SaveResiduoPort,
               DeleteResiduoPort,
               GetResiduoByIdPort,
               GetAllResiduosPort,
               GetResiduosByTipoPort,
               GetTotalesByProductorPort,
               GetResiduosByProductorPort,
               GetResiduosByProductorAndFechasPort {

  private static final String SQL_INSERT =
      "INSERT INTO residuo (id, id_productor, tipo_residuo, peligroso, peso_kg, fecha_generacion) "
      + "VALUES (?, ?, ?, ?, ?, ?)";
  private static final String SQL_SELECT_BY_ID =
      "SELECT id, id_productor, tipo_residuo, peligroso, peso_kg, fecha_generacion "
      + "FROM residuo WHERE id = ? LIMIT 1";
  private static final String SQL_SELECT_ALL =
      "SELECT id, id_productor, tipo_residuo, peligroso, peso_kg, fecha_generacion "
      + "FROM residuo ORDER BY fecha_generacion DESC";
  private static final String SQL_DELETE =
      "DELETE FROM residuo WHERE id = ?";
  private static final String SQL_COUNT_BY_TIPO =
      "SELECT tipo_residuo, COUNT(*) as total FROM residuo "
      + "GROUP BY tipo_residuo ORDER BY total DESC LIMIT ?";
  private static final String SQL_TOTALES_BY_PRODUCTOR =
      "SELECT id_productor, COUNT(*) as cantidad, SUM(peso_kg) as peso_total "
      + "FROM residuo GROUP BY id_productor ORDER BY cantidad DESC";
  private static final String SQL_BY_PRODUCTOR =
      "SELECT id, id_productor, tipo_residuo, peligroso, peso_kg, fecha_generacion "
      + "FROM residuo WHERE id_productor = ? ORDER BY fecha_generacion DESC";
  private static final String SQL_BY_PRODUCTOR_AND_FECHAS =
      "SELECT id, id_productor, tipo_residuo, peligroso, peso_kg, fecha_generacion "
      + "FROM residuo WHERE id_productor = ? AND fecha_generacion BETWEEN ? AND ? "
      + "ORDER BY fecha_generacion DESC";

  private final Connection connection;

  @Override
  public ResiduoModel save(final ResiduoModel residuo) {
    final ResiduoPersistenceDto dto = ResiduoPersistenceMapper.fromModelToDto(residuo);
    executeSave(dto);
    return findByIdOrFail(residuo.getId());
  }

  @Override
  public void delete(final ResiduoId id) {
    try (final PreparedStatement st = connection.prepareStatement(SQL_DELETE)) {
      st.setString(1, id.value());
      st.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseDeleteFailed(id.value(), ex);
    }
  }

  @Override
  public Optional<ResiduoModel> getById(final ResiduoId id) {
    try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      st.setString(1, id.value());
      final ResultSet rs = st.executeQuery();
      if (!rs.next()) return Optional.empty();
      return Optional.of(ResiduoPersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindByIdFailed(id.value(), ex);
    }
  }

  @Override
  public List<ResiduoModel> getAll() {
    try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet rs = st.executeQuery();
      return ResiduoPersistenceMapper.fromResultSetToModelList(rs);
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  @Override
  public List<Map<String, Object>> getCountByTipo(final int limit) {
    try (final PreparedStatement st = connection.prepareStatement(SQL_COUNT_BY_TIPO)) {
      st.setInt(1, limit);
      final ResultSet rs = st.executeQuery();
      final List<Map<String, Object>> result = new ArrayList<>();
      while (rs.next()) {
        final Map<String, Object> row = new HashMap<>();
        row.put("tipo_residuo", rs.getString("tipo_residuo"));
        row.put("total", rs.getLong("total"));
        result.add(row);
      }
      return result;
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  @Override
  public List<Map<String, Object>> getTotalesByProductor() {
    try (final PreparedStatement st = connection.prepareStatement(SQL_TOTALES_BY_PRODUCTOR)) {
      final ResultSet rs = st.executeQuery();
      final List<Map<String, Object>> result = new ArrayList<>();
      while (rs.next()) {
        final Map<String, Object> row = new HashMap<>();
        row.put("id_productor", rs.getString("id_productor"));
        row.put("cantidad",     rs.getLong("cantidad"));
        row.put("peso_total",   rs.getString("peso_total"));
        result.add(row);
      }
      return result;
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  @Override
  public List<ResiduoModel> getByProductor(final String idProductor) {
    try (final PreparedStatement st = connection.prepareStatement(SQL_BY_PRODUCTOR)) {
      st.setString(1, idProductor);
      final ResultSet rs = st.executeQuery();
      return ResiduoPersistenceMapper.fromResultSetToModelList(rs);
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  @Override
  public List<ResiduoModel> getByProductorAndFechas(
      final String idProductor, final LocalDate desde, final LocalDate hasta) {
    try (final PreparedStatement st = connection.prepareStatement(SQL_BY_PRODUCTOR_AND_FECHAS)) {
      st.setString(1, idProductor);
      st.setString(2, desde.toString());
      st.setString(3, hasta.toString());
      final ResultSet rs = st.executeQuery();
      return ResiduoPersistenceMapper.fromResultSetToModelList(rs);
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  private void executeSave(final ResiduoPersistenceDto dto) {
    try (final PreparedStatement st = connection.prepareStatement(SQL_INSERT)) {
      st.setString(1, dto.id());
      st.setString(2, dto.idProductor());
      st.setString(3, dto.tipoResiduo());
      st.setBoolean(4, dto.peligroso());
      st.setString(5, dto.pesoKg());
      st.setString(6, dto.fechaGeneracion());
      st.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseSaveFailed(dto.id(), ex);
    }
  }

  private ResiduoModel findByIdOrFail(final ResiduoId id) {
    return getById(id)
        .orElseThrow(() -> ResiduoNotFoundException.becauseIdWasNotFound(id.value()));
  }
}
