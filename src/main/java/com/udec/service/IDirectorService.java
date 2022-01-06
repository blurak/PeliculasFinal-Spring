package com.udec.service;

import java.util.List;

import com.udec.entity.Direccion;
import com.udec.entity.Director;
import com.udec.entity.Pelicula;
import com.udec.entity.Vistapeliculas;

public interface IDirectorService {

	public void eliminarConCuidado(Integer id);
	
	public Director consultarPorNombrePeliculaQuery(String nombrePelicula);
	
	public Director consultarPorNombrePelicula(Pelicula pelicula);
	
	public Director consultarPorNombrePeliculaJPA(String nombrePelicula);
	
	public void editarConQuery(Direccion direccion);
	
	public List<Vistapeliculas> consultarVista(); 
	
	public Vistapeliculas consultarVistaPorId(Integer id);

}
