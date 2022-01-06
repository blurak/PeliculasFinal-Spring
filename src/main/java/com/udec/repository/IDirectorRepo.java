package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udec.entity.Director;
import com.udec.entity.Pelicula;


@Repository
public interface IDirectorRepo extends JpaRepository<Director, Integer> {
	Director findByNombreIgnoreCase(String nombre);	
	
	@Query("SELECT d FROM Director d LEFT JOIN d.peliculas p WHERE p.nombre = :nombrePelicula")
	Director consultarDirectorPorPelicula(String nombrePelicula);

	Director findByPeliculas_Nombre(String pelicula);
	
	Director findByPeliculas(Pelicula pelicula);
	
}
