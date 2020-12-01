package com.focus.spingboot.backend.apirest.models.dao;

import com.focus.spingboot.backend.apirest.models.api.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IClienteDao extends JpaRepository<Cliente, Long> {
}
