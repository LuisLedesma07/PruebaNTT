package com.luisledesma.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Valid
@Table(name="clientes")
public  class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = true)
	private String primerNombre;
	
	@Column(nullable = true)
	private String segundoNombre;
	
	@Column(nullable = true)
	private String primerApellido;
	
	@Column(nullable = true)
	private String segundoApellido;
	
	@Column(nullable = true)
	private int telefono;
	
	@Column(nullable = true)
	private String direccion;
	
	@Column(nullable = true)
	private String ciudad;
	
	@Column(nullable = false)
	@Pattern(regexp = "(C|P)")
	private String tipoDoc;
	
	@Column(nullable = false, unique = true)
	private int numeroDoc;

	

	
	
}
