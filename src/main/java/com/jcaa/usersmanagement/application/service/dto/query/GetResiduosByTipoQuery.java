package com.jcaa.usersmanagement.application.service.dto.query;

public record GetResiduosByTipoQuery(int limit) {
  public GetResiduosByTipoQuery {
    if (limit <= 0) limit = 10;
  }
}
