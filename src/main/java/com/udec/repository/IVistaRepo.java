package com.udec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.udec.entity.Vistapeliculas;

public interface IVistaRepo extends JpaRepository<Vistapeliculas, Integer>{

	@Query(value = "select p from Vistapeliculas p")
	List<Vistapeliculas> consultarVista();
	
	@Query(value = "select p from Vistapeliculas p where id = :id")
	Vistapeliculas consultarPorId(Integer id);
}
