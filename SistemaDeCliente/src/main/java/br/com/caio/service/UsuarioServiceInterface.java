package br.com.caio.service;

import br.com.caio.dto.UsuarioDTO;

public interface UsuarioServiceInterface {

	UsuarioDTO buscarCliente(String login, String senha);
	
}
