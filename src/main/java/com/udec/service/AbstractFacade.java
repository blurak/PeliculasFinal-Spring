package com.udec.service;

import java.util.List;

public abstract interface AbstractFacade <T, V, S, P>{
	
	public void crear(P entidad);

	public T consultarId(V id);

	public void editar(T entidad);

	public List<T> consultar();
	
	public void eliminar(V id);

	
}
