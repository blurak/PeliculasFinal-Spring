package com.udec.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.udec.entity.Pelicula;
import com.udec.dto.PeliculaDTO;
import com.udec.entity.Director;
import com.udec.exception.ObjectItsCreatedExceptionHandler;
import com.udec.exception.ObjectNotFoundExceptionHandler;
import com.udec.repository.IPeliculaRepo;
import com.udec.service.AbstractFacade;
import com.udec.service.IPeliculaService;

@Service
public class PeliculaServiceImpl implements AbstractFacade<Pelicula, Integer, String, PeliculaDTO>, IPeliculaService {

	@Autowired
	private IPeliculaRepo repoPst;
	
	/////////////////////////// spring data ////////////////////
	@Override
	public void crear(PeliculaDTO pelicula) {
	
		if(pelicula.getDirector() == null) {
			throw new ObjectItsCreatedExceptionHandler("Debe proporcionar un Director para crear esta Pelicula");
		}
		if(repoPst.findByNombreIgnoreCase(pelicula.getNombre()) == null ) {
			ModelMapper mapper = new ModelMapper();
			Pelicula pel = mapper.map(pelicula, Pelicula.class);
			repoPst.save(pel);
		}
		else {
			throw new ObjectItsCreatedExceptionHandler("Ya hay una pelicula creada con ese nombre");
		}
				
	}

	@Override
	public Pelicula consultarId(Integer id) {
		Optional<Pelicula> opc = repoPst.findById(id);
		return opc.get();
	}

	@Override
	public void editar(Pelicula pelicula) {
		if(pelicula.getDirector() == null) {
			throw new ObjectItsCreatedExceptionHandler("Debe proporcionar un Director para editar esta Pelicula");
		}
		if(repoPst.validarSiExisteParaEditarlo(pelicula.getId()) == null) {
			throw new ObjectNotFoundExceptionHandler("Esta pelicula a editar no existe");
		}
		Pelicula peli = repoPst.findByNombreIgnoreCase(pelicula.getNombre());
		if(peli == null) {
			repoPst.save(pelicula);
		}
		else { 
			if(peli.getId() == pelicula.getId()) {
				repoPst.save(pelicula);	
			}
			if(peli.getId() != pelicula.getId()) {
				throw new ObjectItsCreatedExceptionHandler("No puede poner este nombre porque ya existe");
			}
		}		
	}

	@Override
	public List<Pelicula> consultar() {
		List<Pelicula> lista =  repoPst.findAll(Sort.by(Sort.Direction.DESC, "nombre"));
		return lista;
	}

	@Override
	public void eliminar(Integer id) {
		repoPst.deleteById(id);		
	}
}
