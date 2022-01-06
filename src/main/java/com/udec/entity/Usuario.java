package com.udec.entity;

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

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "documento", nullable = false, length = 30, unique = true)
	@NotNull(message = "el numero es requerido")
	@Size(min = 7 , max = 29, message= "Longitud del documento no valida")
	private String documento;
	
	@Column(name = "nombre", nullable = false, length = 25)
	@NotNull(message = "el nombre es requerido")
	@Size(min = 3 , max = 24, message= "Longitud del nombre no valida")
	private String nombre;
	
	@Column(name = "apellido", nullable = false, length = 25)
	@NotNull(message = "el apellido es requerido")
	@Size(min = 3 , max = 24, message= "Longitud del apellido no valida")
	private String apellido;
	
	@Column(name = "nick", nullable = false, unique = true)
	@Size(min = 3 , max = 24, message= "Longitud del apellido no valida")
	@NotNull(message = "el nick es requerido")
	private String nick;
	
	@Column(name = "clave", columnDefinition = "TEXT", nullable = false)
	@NotNull(message = "la clave es requerido")
	private String clave;
	
	@Column(name = "estado", nullable = false)
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "idRol", foreignKey = @ForeignKey(name = "FK_rol") )
	private Rol rol;
	
	public Usuario() {
		
	}
	
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
}
