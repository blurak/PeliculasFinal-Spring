package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.udec.entity.Pelicula;


@Repository
public interface IPeliculaRepo extends JpaRepository<Pelicula, Integer> {
	//@Query(value = "select p from Pelicula p where p.nombre ilike :nombre ")
	public Pelicula findByNombreIgnoreCase(String nombre);
	
	@Query(value="select p from Pelicula p where p.id = :id")
	public Pelicula validarSiExisteParaEditarlo(Integer id);
	
}
