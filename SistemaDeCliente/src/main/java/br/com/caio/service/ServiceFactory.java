package br.com.caio.service;

import br.com.caio.model.ClienteRepositoryInterface;
import br.com.caio.model.UsuarioRepositoryInterface;

public class ServiceFactory {

	public static ClienteServiceInterface 
		criaClienteService(ClienteRepositoryInterface repositorio){
		return new ClienteService(repositorio);
	}
	
	public static UsuarioServiceInterface 
	criaUsuarioService(UsuarioRepositoryInterface repositorio){
	return new UsuarioService(repositorio);
	}
	
}
