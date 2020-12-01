package com.focus.spingboot.backend.apirest.controllers;

import com.focus.spingboot.backend.apirest.models.api.Cliente;
import com.focus.spingboot.backend.apirest.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> index(){
        return clienteService.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try{
            cliente = clienteService.findById(id);
        }catch (DataAccessException dt){
            response.put("mensaje", "Error al realizar la consulta en base de datos ");
            response.put("error", dt.getMessage().concat(": ").concat(dt.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(cliente == null){
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat("No existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Cliente cliente){
        Cliente clienteNew = null;

        Map<String, Object> response = new HashMap<>();
        try{
            clienteNew = clienteService.save(cliente);
        }catch (DataAccessException dt){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", dt.getMessage().concat(": ").concat(dt.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El cliente se ha creado con exito");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id){
        Cliente clienteActual = clienteService.findById(id);
        Cliente clienteUpdate = null;
        Map<String, Object> response = new HashMap<>();
        if(clienteActual != null){
            response.put("mensaje", "Error: no se pudo editar el cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setEmail(cliente.getEmail());
            clienteUpdate = clienteService.save(clienteActual);
        }catch (DataAccessException dt){
            response.put("mensaje", "Error al actualizar el insert en la base de datos");
            response.put("error", dt.getMessage().concat(": ").concat(dt.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El cliente se ha sido actualizado con exito");
        response.put("cliente", clienteUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try{
            clienteService.delete(id);
        }catch (DataAccessException dt){
            response.put("mensaje", "Error al actualizar el insert en la base de datos");
            response.put("error", dt.getMessage().concat(": ").concat(dt.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
