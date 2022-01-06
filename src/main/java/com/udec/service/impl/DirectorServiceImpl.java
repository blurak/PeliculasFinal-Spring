package com.udec.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.udec.entity.Direccion;
import com.udec.entity.Director;
import com.udec.dto.DirectorDTO;
import com.udec.dto.PeliculaDTO;
import com.udec.entity.Pelicula;
import com.udec.entity.Vistapeliculas;
import com.udec.exception.ObjectItsCreatedExceptionHandler;
import com.udec.exception.ObjectNotFoundExceptionHandler;
import com.udec.repository.IDireccionRepo;
import com.udec.repository.IDirectorRepo;
import com.udec.repository.IVistaRepo;
import com.udec.service.AbstractFacade;
import com.udec.service.IDirectorService;

@Service
public class DirectorServiceImpl implements AbstractFacade<Director, Integer, String, DirectorDTO>, IDirectorService{

	@Autowired
	private IDirectorRepo repo;
	
	@Autowired
	private IDireccionRepo dirRepo;
	
	@Autowired
	private IVistaRepo vistaRepo;
	
	//@Autowired
	//private IPeliculaRepo repoPelicula;

	@Override
	public void crear(DirectorDTO director) {
		
		Director consulta = repo.findByNombreIgnoreCase(director.getNombre());
		if(consulta == null) {
			if(director.getPeliculas() != null) {
				for(PeliculaDTO pe : director.getPeliculas()) {
					pe.setDirector(director);
				}
				for(PeliculaDTO pe : director.getPeliculas()) {
					if(repo.findByPeliculas_Nombre(pe.getNombre())!= null) {
						throw new ObjectItsCreatedExceptionHandler("ya existe una pelicula con ese nombre");
					}
				}
			}
			
			ModelMapper mapper = new ModelMapper();
			Director dir = mapper.map(director, Director.class);
			dir.getDireccion().setDirector(dir);
			repo.save(dir);
		}
		else {
			throw new ObjectItsCreatedExceptionHandler("No puede poner este nombre porque ya existe");
		}
	}

	@Override
	public Director consultarId(Integer id) {
		Optional<Director> director = repo.findById(id);
		return director.get();
	}

	@Override
	public void editar(Director obj) {
		if(obj.getId() == null) {
			throw new ObjectItsCreatedExceptionHandler(" Id Director es requerido ");
		}
		Director consulta = repo.findByNombreIgnoreCase(obj.getNombre());
		if(obj.getDireccion() != null) {
			if(consulta == null) {
				Director director = repo.findById(obj.getId()).orElseThrow(()-> new ObjectNotFoundExceptionHandler("Director no existe"));
				obj.getDireccion().setDirector(director);
				director.setCorreo(obj.getCorreo());
				director.setNombre(obj.getNombre());
				director.setDireccion(obj.getDireccion());
				repo.save(director);
			}
			else {
				if(consulta.getId() == obj.getId()) {
					Director director = repo.findById(obj.getId()).orElseThrow(()-> new ObjectNotFoundExceptionHandler("Director no existe"));
					obj.getDireccion().setDirector(director);
					director.setCorreo(obj.getCorreo());
					director.setNombre(obj.getNombre());
					director.setDireccion(obj.getDireccion());
					repo.save(director);
				}
				else{
					throw new ObjectItsCreatedExceptionHandler("No puede poner este nombre porque ya existe");
				}
			}
		}
		else {
			editarSinDireccion(obj, consulta);
		}		
	}

	@Override
	public List<Director> consultar() {
		return repo.findAll(Sort.by(Sort.Direction.DESC, "nombre"));
	}
	
	@Override
	public void eliminarConCuidado(Integer id) {
		Director director = repo.findById(id).orElseThrow(()-> new ObjectNotFoundExceptionHandler("Director no existe"));
		if(director.getPeliculas().size()==0) {
			repo.delete(director);
		}
		else {
			throw new ObjectItsCreatedExceptionHandler(" No se puede borrar este Director porque tiene peliculas relacionadas a él ");
		}
	}

	@Override
	public void eliminar(Integer id) {
		Director director = repo.findById(id).orElseThrow(()-> new ObjectNotFoundExceptionHandler("Director no existe"));
		/*if(director.getPeliculas() != null) {
			for(Pelicula p : director.getPeliculas()) {
				repoPelicula.delete(p);
			}
		}*/
		repo.delete(director);		
	}

	@Override
	public Director consultarPorNombrePeliculaQuery(String nombrePelicula) {
		if(repo.consultarDirectorPorPelicula(nombrePelicula) == null  ) {
			throw new ObjectNotFoundExceptionHandler("Esa pelicula no existe");
		}else {
			return repo.consultarDirectorPorPelicula(nombrePelicula);
		}
		
	}
	@Override
	public Director consultarPorNombrePelicula(Pelicula pelicula) {
		if(repo.findByPeliculas(pelicula) == null  ) {
			throw new ObjectNotFoundExceptionHandler("Esa pelicula no existe");
		}else {
			return repo.findByPeliculas(pelicula);
		}
	}

	@Override
	public Director consultarPorNombrePeliculaJPA(String nombrePelicula) {
		if(repo.findByPeliculas_Nombre(nombrePelicula) == null  ) {
			throw new ObjectNotFoundExceptionHandler("Esa pelicula no existe");
		}else {
			return repo.findByPeliculas_Nombre(nombrePelicula);
		}
	}
	public void editarSinDireccion(Director obj, Director consulta) {
		if(consulta == null) {
			Director director = repo.findById(obj.getId()).orElseThrow(()-> new ObjectNotFoundExceptionHandler("Director no existe"));
			director.setCorreo(obj.getCorreo());
			director.setNombre(obj.getNombre());
			repo.save(director);
		}
		else {
			if(consulta.getId() == obj.getId()) {
				Director director = repo.findById(obj.getId()).orElseThrow(()-> new ObjectNotFoundExceptionHandler("Director no existe"));
				director.setCorreo(obj.getCorreo());
				director.setNombre(obj.getNombre());
				repo.save(director);
			}
			else{
				throw new ObjectItsCreatedExceptionHandler("No puede poner este nombre porque ya existe");
			}
		}
	}

	@Override
	public void editarConQuery(Direccion entidad) {
		
		Direccion di =dirRepo.findById(entidad.getId()).orElseThrow(()-> new ObjectNotFoundExceptionHandler("No se econtró direccion con id: "+ entidad.getId()+" para editar"));
		if(di.getId() == entidad.getId()) {
			dirRepo.editarDireccion(entidad.getDireccion(), entidad.getBarrio(), entidad.getId());
		}
	}

	@Override
	public List<Vistapeliculas> consultarVista() {
		
		return vistaRepo.consultarVista();
	}

	@Override
	public Vistapeliculas consultarVistaPorId(Integer id) {
		if(vistaRepo.consultarPorId(id) != null) {
			return vistaRepo.consultarPorId(id);
		}
		else {
			throw new ObjectNotFoundExceptionHandler("no se encontró el director con id: " + id.toString());
		}
	}
	
}
