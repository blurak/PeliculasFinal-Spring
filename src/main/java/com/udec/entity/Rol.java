package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {
	
	@Id
	private Integer idRol;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	public Rol() {
		
	}
	
	public Rol(Integer id, String nombre, String descripcion) {
		super();
		this.idRol = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}



	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer id) {
		this.idRol = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	

}
