package com.udec.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class PeliculaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value ="Identificador de la pelicula")
	private Integer id;
	
	@NotNull(message = "Nombre es campo obligatorio")
	@Size(min = 2,  max = 20, message = "Nombre entre 2 y 20 carácteres")
	@ApiModelProperty(value ="Nombre de la pelicula")
	private String nombre;
	
	@NotNull(message = "Debe especificar una duracion")
	@Size(min = 2,  max = 15, message = "duracion entre 2 y 15 carácteres")
	@ApiModelProperty(value ="Duracion de la pelicula")
	private String duracion;
	
	@NotNull(message = "Ingrese genero de la pelicula")
	@Size(min = 2,  max = 50, message = "Genero entre 2 y 50 carácteres")
	@ApiModelProperty(value ="Genero de la pelicula")
	private String genero;
	
	@ApiModelProperty(value ="Fecha de lanzamiento de la pelicula")
	private LocalDate fechalanzamiento;
	
	private DirectorDTO director;
	
	public PeliculaDTO() {
		
	}
	public PeliculaDTO(String nombre, int id, String duracion, String genero, DirectorDTO director, LocalDate fechalanzamiento) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.duracion = duracion;
		this.genero = genero;
		this.director = director;
		this.fechalanzamiento = fechalanzamiento;
	}
	public String serializar(PeliculaDTO pelicula) {
		String peliculaString;
		peliculaString = pelicula.getNombre()+","+Integer.toString(pelicula.getId()) +","+ pelicula.getDuracion()+ "," +pelicula.getGenero()+";";
		return peliculaString;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public DirectorDTO getDirector() {
		return director;
	}
	public void setDirector(DirectorDTO director) {
		this.director = director;
	}
	public LocalDate getFechalanzamiento() {
		return fechalanzamiento;
	}
	public void setFechalanzamiento(LocalDate fechalanzamiento) {
		this.fechalanzamiento = fechalanzamiento;
	}
	

	
}
