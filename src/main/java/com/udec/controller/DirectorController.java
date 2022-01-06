package com.udec.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udec.dto.DirectorDTO;
import com.udec.entity.Direccion;
import com.udec.entity.Director;
import com.udec.entity.Pelicula;
import com.udec.entity.Vistapeliculas;
import com.udec.exception.ObjectNotFoundExceptionHandler;
import com.udec.service.AbstractFacade;
import com.udec.service.IDirectorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@PreAuthorize("hasAuthority('admin')")
@Controller
@RestController
@Validated
@RequestMapping("/directores")
@Api(value="Servicio de Directores ", description = "Este servicio gestiona un CRUD de directores")
public class DirectorController {
	@Autowired
	AbstractFacade<Director, Integer, String, DirectorDTO> service;
	
	@Autowired
	IDirectorService serv;
	
	@GetMapping("/consultar")
	@ApiOperation(value="Metodo que retorna todos los directores creados")
	public ResponseEntity<List<Director>> consultar() throws ObjectNotFoundExceptionHandler{	
			return new ResponseEntity<List<Director>>(service.consultar(), HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	@ApiOperation(value="Metodo que crea un director")
	public ResponseEntity<Object> crear(@Valid @RequestBody DirectorDTO director){
		service.crear(director);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@GetMapping("/filtrar/{id}")
	@ApiOperation(value="Metodo que retorna un director por ID")
	public ResponseEntity<Director> filtrar(@PathVariable Integer id){ 
			return new ResponseEntity<Director>(service.consultarId(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminarConCuidado/{id}")
	@ApiOperation(value="Metodo que elimina un director especifico por ID teniendo en cuenta que tiene peliculas asociadas")
	public ResponseEntity<Object> eliminarConCuidado(@PathVariable Integer id){
		serv.eliminarConCuidado(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/eliminarDeChorro/{id}")
	@ApiOperation(value="Metodo que elimina un director especifico por ID sin tener en cuenta que tiene peliculas asociadas")
	public ResponseEntity<Object> eliminarDeChorro(@PathVariable Integer id){
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/editar")
	@ApiOperation(value="Metodo que edita un director")
	public ResponseEntity<Object> editar(@Valid @RequestBody Director director){
		service.editar(director);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PutMapping("/editardireccionporquery")
	@ApiOperation(value="Metodo que edita una direccion de un director por query")
	public ResponseEntity<Object> editarPorQuery(@Valid @RequestBody Direccion direccion){
		serv.editarConQuery(direccion);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@GetMapping("/obtenerDirectorQuery/{nombrePelicula}")
	@ApiOperation(value="Metodo que retorna un director por el nombre de la pelicula")
	public ResponseEntity<Director> buscarDirectorPorNombrePeliculaQuery(@PathVariable String nombrePelicula){ 
			return new ResponseEntity<Director>(serv.consultarPorNombrePeliculaQuery(nombrePelicula) , HttpStatus.OK);
	}
	
	@GetMapping("/obtenerDirectorJPA/{nombrePelicula}")
	@ApiOperation(value="Metodo que retorna un director por el nombre de la pelicula")
	public ResponseEntity<Director> buscarDirectorPorNombrePeliculaJPA(@PathVariable String nombrePelicula){ 
			return new ResponseEntity<Director>(serv.consultarPorNombrePeliculaJPA(nombrePelicula) , HttpStatus.OK);
	}
	
	@GetMapping("/obtenerDirector")
	@ApiOperation(value="Metodo que retorna ")
	public ResponseEntity<Director> consultarPorPelicula(@RequestBody Pelicula pelicula) throws ObjectNotFoundExceptionHandler{	
			return new ResponseEntity<Director>(serv.consultarPorNombrePelicula(pelicula), HttpStatus.OK);
	}
	
	@GetMapping("/obtenerPorVista")
	@ApiOperation(value="Metodo que retorna por vista")
	public ResponseEntity<List<Vistapeliculas>> consultarPorvista() throws ObjectNotFoundExceptionHandler{	
			return new ResponseEntity<List<Vistapeliculas>>(serv.consultarVista(), HttpStatus.OK);
	}
	
	@GetMapping("/obtenerVistaPorId/{id}")
	@ApiOperation(value="Metodo que retorna vista filtrada")
	public ResponseEntity<Vistapeliculas> consultarPorVistaId(@PathVariable Integer id) throws ObjectNotFoundExceptionHandler{	
			return new ResponseEntity<Vistapeliculas>(serv.consultarVistaPorId(id) , HttpStatus.OK);
	}
	
}
