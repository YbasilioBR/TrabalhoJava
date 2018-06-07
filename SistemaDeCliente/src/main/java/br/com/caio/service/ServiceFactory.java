package br.com.caio.service;

import br.com.caio.model.ClienteRepositoryInterface;

public class ServiceFactory {

	public static ClienteServiceInterface 
		criaClienteService(ClienteRepositoryInterface repositorio){
		return new ClienteService(repositorio);
	}
	
}
