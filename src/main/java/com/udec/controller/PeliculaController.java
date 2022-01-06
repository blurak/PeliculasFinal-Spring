package com.udec.controller;

import java.sql.SQLException;
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

import com.udec.dto.PeliculaDTO;
import com.udec.entity.Pelicula;
import com.udec.service.AbstractFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@PreAuthorize("hasAuthority('espectador')")
@Controller
@RestController
@Validated
@RequestMapping("/peliculas")
@Api(value="Servicio de Peliculas ", description = "Este servicio gestiona un CRUD de peliculas")
public class PeliculaController {

	@Autowired
	AbstractFacade<Pelicula, Integer, String, PeliculaDTO> servicio;
	
	////////// Spring Data ///////77
	@PostMapping("/crearpst")
	@ApiOperation(value="Metodo que crea una pelicula en bd por framework")
	public ResponseEntity<Object> crearPst(@Valid @RequestBody PeliculaDTO pelicula){
		servicio.crear(pelicula);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@GetMapping("/consultartodopst")
	@ApiOperation(value="Metodo que retorna todas las peliculas creadas en bd por framework")
	public ResponseEntity<List<Pelicula>> consultarTodoPst() throws SQLException{	
			return new ResponseEntity<List<Pelicula>>(servicio.consultar() , HttpStatus.OK);
	}
	
	@GetMapping("/filtrarpst/{id}")
	@ApiOperation(value="Metodo que retorna una pelicula por ID")
	public ResponseEntity<Pelicula> filtrarPst(@PathVariable Integer id){ 
			return new ResponseEntity<Pelicula>(servicio.consultarId(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminarpst/{id}")
	@ApiOperation(value="Metodo que elimina una pelicula especifica por ID en BD por framework")
	public ResponseEntity<Object> eliminarpst(@PathVariable Integer id){
		servicio.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/editarpst")
	@ApiOperation(value="Metodo que edita una pelicula por framework")
	public ResponseEntity<Object> editarPst(@Valid @RequestBody Pelicula pelicula){
		servicio.editar(pelicula);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
