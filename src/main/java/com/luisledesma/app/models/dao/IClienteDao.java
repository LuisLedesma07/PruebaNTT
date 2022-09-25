package com.luisledesma.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luisledesma.app.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Integer> {

}
