package br.com.caio.model;

import br.com.caio.dto.UsuarioDTO;;

public interface UsuarioRepositoryInterface {
	
	UsuarioDTO buscarUsuario(String login, String senha);

}
