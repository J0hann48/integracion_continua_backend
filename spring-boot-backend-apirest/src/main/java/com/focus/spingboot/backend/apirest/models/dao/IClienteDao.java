package com.focus.spingboot.backend.apirest.models.dao;

import com.focus.spingboot.backend.apirest.models.api.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long> {
}
