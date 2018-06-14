package br.com.caio.service;

import br.com.caio.dto.UsuarioDTO;
import br.com.caio.model.UsuarioRepositoryInterface;;

public class UsuarioService implements UsuarioServiceInterface {
	
private UsuarioRepositoryInterface repositorio;
	
	public UsuarioService(UsuarioRepositoryInterface repositorio){
		this.repositorio = repositorio;
	}

	public UsuarioDTO buscarCliente(String login, String senha) {
		return repositorio.buscarUsuario(login, senha);
	}
	
}
