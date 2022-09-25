package com.luisledesma.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisledesma.app.models.entity.Cliente;
import com.luisledesma.app.models.services.IClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;


	private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable int id){
		log.info("Consultado cliente ****");
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en bd");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			log.info("Error al consultar el cliente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}





		if(cliente == null) {
			response.put("mensaje", "El cliente con el ID: ".concat(String.valueOf(id).concat(" no existe en la base de datos")));
			log.error("Error al consultar el cliente, cliente no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		log.info("Se consultó el cliente con éxito" + cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		log.info("Creando cliente ***");
		Cliente clienteNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err ->"El campo '" + err.getField() + "'" + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			cliente.setPrimerApellido("Ledesma");
			cliente.setSegundoApellido("Mariño");
			cliente.setPrimerNombre("Luis");
			cliente.setSegundoNombre("David");
			cliente.setCiudad("Pereira");
			cliente.setDireccion("Dosquebradas 26");
			cliente.setTelefono(335);
			
			clienteNuevo = clienteService.save(cliente);
			log.info("Cliente creado con éxito " + clienteNuevo);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al crear nuevo usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			log.error("Error al crear el cliente en la bd");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido creado con éxito");
		response.put("cliente", clienteNuevo);
		return new ResponseEntity<Map<String, Object>>( response, HttpStatus.CREATED);
	}
	
	
}
