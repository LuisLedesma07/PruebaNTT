package com.luisledesma.app.models.services;

import java.util.List;

import com.luisledesma.app.models.entity.Cliente;

public interface IClienteService {

	
	public List<Cliente> findAll();
	
	public Cliente findById(int id);
	
	Cliente save(Cliente cliente);
	
	public void delete(int id);
	
}
