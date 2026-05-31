package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.exception.InvalidResiduoPesoException;
import com.jcaa.usersmanagement.domain.exception.InvalidResiduoTipoException;
import com.jcaa.usersmanagement.domain.valueobject.ResiduoId;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Value;

@Value
public class ResiduoModel {

  ResiduoId   id;
  String      idProductor;
  String      tipoResiduo;
  boolean     peligroso;
  BigDecimal  pesoKg;
  LocalDate   fechaGeneracion;

  public ResiduoModel(
      final ResiduoId  id,
      final String     idProductor,
      final String     tipoResiduo,
      final boolean    peligroso,
      final BigDecimal pesoKg,
      final LocalDate  fechaGeneracion) {

    validateTipo(tipoResiduo);
    validatePeso(pesoKg);

    this.id              = Objects.requireNonNull(id, "ResiduoId cannot be null");
    this.idProductor     = Objects.requireNonNull(idProductor, "idProductor cannot be null").trim();
    this.tipoResiduo     = tipoResiduo.trim();
    this.peligroso       = peligroso;
    this.pesoKg          = pesoKg;
    this.fechaGeneracion = Objects.requireNonNull(fechaGeneracion, "fechaGeneracion cannot be null");
  }

  public static ResiduoModel create(
      final ResiduoId  id,
      final String     idProductor,
      final String     tipoResiduo,
      final boolean    peligroso,
      final BigDecimal pesoKg) {
    return new ResiduoModel(id, idProductor, tipoResiduo, peligroso, pesoKg, LocalDate.now());
  }

  private static void validateTipo(final String tipo) {
    final String trimmed = Objects.requireNonNull(tipo, "tipoResiduo cannot be null").trim();
    if (trimmed.isEmpty()) {
      throw InvalidResiduoTipoException.becauseValueIsEmpty();
    }
    if (trimmed.length() > 100) {
      throw InvalidResiduoTipoException.becauseValueIsTooLong(trimmed);
    }
  }

  private static void validatePeso(final BigDecimal peso) {
    if (Objects.isNull(peso)) {
      throw InvalidResiduoPesoException.becauseValueIsNull();
    }
    if (peso.compareTo(BigDecimal.ZERO) <= 0) {
      throw InvalidResiduoPesoException.becauseValueIsNotPositive(peso);
    }
  }
}
