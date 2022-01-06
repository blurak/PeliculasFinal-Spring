package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="direccion")

public class Direccion {

	@ApiModelProperty(value ="Identificador de la direccion")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Direccion es campo obligatorio")
	@Size(min = 2,  max = 20, message = "direccion entre 2 y 30 carácteres")
	@ApiModelProperty(value ="Direccion del Director")
	@Column(name="direccion", nullable = false)
	private String direccion;
	
	@NotNull(message = "Barrio es campo obligatorio")
	@Size(min = 2,  max = 20, message = "Barrio entre 2 y 20 carácteres")
	@ApiModelProperty(value ="barrio del Director")
	@Column(name="barrio", nullable = false)
	private String barrio;
	
	
	@OneToOne
	@JoinColumn(name="id_director", nullable=false, foreignKey = @ForeignKey(name="FK_Director_Direccion"))
	private Director director;

	public Direccion() {
		
	}

	public Direccion(Integer id, String direccion, String barrio, Director director) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.barrio = barrio;
		this.director = director;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	@JsonIgnore
	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}
	
	
}
