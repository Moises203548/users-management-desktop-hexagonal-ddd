package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteResiduoPort;
import com.jcaa.usersmanagement.application.port.out.GetAllResiduosPort;
import com.jcaa.usersmanagement.application.port.out.GetResiduoByIdPort;
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
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class ResiduoRepositoryMySQL
        implements SaveResiduoPort,
        DeleteResiduoPort,
        GetResiduoByIdPort,
        GetAllResiduosPort {

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
      if (!rs.next()) {
        return Optional.empty();
      }
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