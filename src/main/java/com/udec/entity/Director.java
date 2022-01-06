package com.udec.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.annotations.VisibleForTesting;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="director")
public class Director {
	
	@ApiModelProperty(value ="Identificador del director")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Nombre es campo obligatorio")
	@Size(min = 2,  max = 20, message = "Nombre entre 2 y 20 carácteres")
	@ApiModelProperty(value ="Nombre del director")
	@Column(name="nombre", nullable = false)
	private String nombre;	
	
	@ApiModelProperty(value ="Un correo para validaciones")
	@Column(name="correo", nullable=false)
	@Email(message = "Correo no valido", regexp ="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String correo;
	
	@OneToMany(mappedBy ="director", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Pelicula> peliculas;
	
	@OneToOne(mappedBy ="director", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Direccion direccion;
	
	public Director() {
		
	}

	public Director(Integer id, String nombre,String correo, List<Pelicula> peliculas, Direccion direccion) {
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
	
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}	
}
