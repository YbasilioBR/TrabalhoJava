package br.com.caio.service;

import java.util.List;

import br.com.caio.dto.ClienteDTO;
import br.com.caio.dto.ClienteListaDTO;

public interface ClienteServiceInterface {

	void salvar(ClienteDTO dto);

	void excluir(Long id);

	List<ClienteListaDTO> buscarTodos(String nome);

	ClienteDTO buscarCliente(Long id);

}