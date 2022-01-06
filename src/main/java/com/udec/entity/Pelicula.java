package com.udec.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="pelicula")

public class Pelicula {

	@ApiModelProperty(value ="Identificador de la pelicula")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Nombre es campo obligatorio")
	@Size(min = 2,  max = 20, message = "Nombre entre 2 y 20 carácteres")
	@ApiModelProperty(value ="Nombre de la pelicula")
	@Column(name="nombre", nullable = false)
	private String nombre;
	
	@NotNull(message = "Debe especificar una duracion")
	@Size(min = 2,  max = 15, message = "duracion entre 2 y 15 carácteres")
	@ApiModelProperty(value ="Duracion de la pelicula")
	@Column(name="duracion", nullable = false)
	private String duracion;
	
	@NotNull(message = "Ingrese genero de la pelicula")
	@Size(min = 2,  max = 50, message = "Genero entre 2 y 50 carácteres")
	@ApiModelProperty(value ="Genero de la pelicula")
	@Column(name="genero", nullable = false)
	private String genero;
	
	@ApiModelProperty(value ="Fecha de lanzamiento de la pelicula")
	@Column(name="fechalanzamiento", nullable=true)
	private LocalDate fechalanzamiento;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name="id_director", nullable=false, foreignKey = @ForeignKey(name="FK_Director_Pelicula"))
	private Director director;
	
	
	public Pelicula() {
		
	}
	
	public Pelicula(Integer id, String nombre, String duracion, String genero, LocalDate fechalanzamiento, Director director) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.genero = genero;
		this.fechalanzamiento = fechalanzamiento;
		this.director=director;
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

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public LocalDate getFechalanzamiento() {
		return fechalanzamiento;
	}

	public void setFechalanzamiento(LocalDate fechalanzamiento) {
		this.fechalanzamiento = fechalanzamiento;
	}
	@JsonIgnore
	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}
	
	
}
