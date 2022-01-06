package com.udec.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udec.entity.Direccion;

@Repository
public interface IDireccionRepo extends JpaRepository<Direccion, Integer>{
	
	@Query(value="UPDATE direccion SET direccion = ?1, barrio = ?2 where id = ?3", nativeQuery = true)
	@Modifying
	@Transactional
	public void editarDireccion(String direccion, String barrio, Integer id);

	@Query(value="Select director.id from director, direccion where direccion.id_director = director.id and director.id = ?1 and direccion.id = ?2", nativeQuery = true)
	public Integer encontrarDirectorPorID(Integer id, Integer direccion);
	
	
}
