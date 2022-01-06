package com.udec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udec.entity.Usuario;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{

	Object findByDocumento(String documento);

	Usuario findByNick(String nick);
	
	Optional<Usuario> findById(Integer id);

}
