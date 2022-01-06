package com.udec.dto;

import java.util.List;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.udec.entity.Direccion;

import io.swagger.annotations.ApiModelProperty;


public class DirectorDTO {
	
	@ApiModelProperty(value ="Identificador del director")
	private Integer id;
	
	@NotNull(message = "Nombre es campo obligatorio")
	@Size(min = 2,  max = 20, message = "Nombre entre 2 y 20 carácteres")
	@ApiModelProperty(value ="Nombre del director")
	private String nombre;	
	
	@ApiModelProperty(value ="Un correo para validaciones")
	@Email(message = "Correo no valido", regexp ="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String correo;
	
	private Direccion direccion;
	
	private List<PeliculaDTO> peliculas;

	public DirectorDTO() {
		
	}

	public DirectorDTO(Integer id, String nombre, String correo, List<PeliculaDTO> peliculas, Direccion direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.peliculas = peliculas;
		this.direccion = direccion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<PeliculaDTO> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<PeliculaDTO> peliculas) {
		this.peliculas = peliculas;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	
}
