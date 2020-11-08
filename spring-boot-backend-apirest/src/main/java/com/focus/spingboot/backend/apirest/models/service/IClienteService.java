package com.focus.spingboot.backend.apirest.models.service;

import com.focus.spingboot.backend.apirest.models.api.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> findAll();
    public Cliente findById(Long id);
    public Cliente save(Cliente cliente);
    public void delete(Long id);
}
