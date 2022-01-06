package com.udec.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.udec.entity.Usuario;


public interface IUsuarioService extends UserDetailsService {
	
	public void crear(Usuario entidad);

	public Usuario consultarId(Integer id);

	public void editar(Usuario entidad);

	public List<Usuario> consultar();
	
	public void eliminar(Integer id);

}
