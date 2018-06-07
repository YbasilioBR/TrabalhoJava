package br.com.caio.service;

import java.util.List;

import br.com.caio.dto.ClienteDTO;
import br.com.caio.dto.ClienteListaDTO;
import br.com.caio.model.ClienteRepositoryInterface;

public class ClienteService implements ClienteServiceInterface {

	private ClienteRepositoryInterface repositorio;
	
	public ClienteService(ClienteRepositoryInterface repositorio){
		this.repositorio = repositorio;
	}
	
	/* (non-Javadoc)
	 * @see br.com.caio.service.ClienteServiceInterface#salvar(br.com.caio.dto.ClienteDTO)
	 */
	public void salvar(ClienteDTO dto) {
		repositorio.salvar(dto);
	}

	/* (non-Javadoc)
	 * @see br.com.caio.service.ClienteServiceInterface#excluir(java.lang.Long)
	 */
	public void excluir(Long id) {
		repositorio.excluir(id);
	}

	/* (non-Javadoc)
	 * @see br.com.caio.service.ClienteServiceInterface#buscarTodos()
	 */
	public List<ClienteListaDTO> buscarTodos(String nome) {
		return repositorio.buscarTodos(nome);
	}

	/* (non-Javadoc)
	 * @see br.com.caio.service.ClienteServiceInterface#buscarCliente(java.lang.Long)
	 */
	public ClienteDTO buscarCliente(Long id) {
		return repositorio.buscarCliente(id);
	}

}
