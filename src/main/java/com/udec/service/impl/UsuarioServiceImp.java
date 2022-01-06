package com.udec.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.udec.entity.Usuario;
import com.udec.exception.ObjectItsCreatedExceptionHandler;
import com.udec.exception.ObjectNotFoundExceptionHandler;
import com.udec.repository.IUsuarioRepo;
import com.udec.service.IUsuarioService;

@Service
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario us = repo.findByNick(username);
		if(us == null) {
			throw new ObjectNotFoundExceptionHandler("Nick o contraseña incorrectos");			
		}
		if(us.isEstado() == false) {
			throw new ObjectItsCreatedExceptionHandler("Usuario no habilitado");
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(us.getRol().getNombre()));
		
		UserDetails udt = new User(us.getNick(), us.getClave(), roles);
		return udt;
		
	}
	
	
	@Override
	public void crear(Usuario entidad) {
		
		if (repo.findByDocumento(entidad.getDocumento()) != null) {
			throw new ObjectItsCreatedExceptionHandler(entidad.getNombre() + " ya existe.");
		} else {
			entidad.setClave(bcrypt.encode(entidad.getClave()));
			repo.save(entidad);
		}
		
	}

	@Override
	public Usuario consultarId(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void editar(Usuario entidad) {
		Usuario us = repo.findById(entidad.getId()).get();
		if(us != null) {
			repo.save(entidad);
		}
		else {
			throw new ObjectNotFoundExceptionHandler(" Este usuario a editar no existe ");
		}
	}

	@Override
	public List<Usuario> consultar() {
		
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		Usuario us = repo.findById(id).get();
		if(us != null) {
			repo.deleteById(id);
		}
		else {
			throw new ObjectNotFoundExceptionHandler(" Este usuario a eliminar no existe ");
		}
	}
	
	
}
