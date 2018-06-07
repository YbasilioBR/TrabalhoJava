package br.com.caio.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caio.dto.ClienteDTO;
import br.com.caio.dto.ClienteListaDTO;

public class ClienteRepository implements ClienteRepositoryInterface {

	private ConnectionFactory connectionFactory;

	public ClienteRepository(ConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caio.service.ClienteServiceInterface#salvar(br.com.caio.dto.
	 * ClienteDTO)
	 */
	public void salvar(ClienteDTO dto) {
		Boolean insert=true;
		String sql = "insert into cliente (nome,rua,bairro,"
				+ "cep,numero,estado,telefone,cpf) "
				+ "values (?,?,?,?,?,?,?,?)";
		if (dto.getId() != null){
			insert=false;
		}
		
		if(!insert){
			sql =  "UPDATE cliente SET "
					+ "nome = ?, "
					+ "rua = ?, "
					+ "bairro = ?, "
					+ "cep = ?, "
					+ "numero = ?, "
					+ "estado = ?, "
					+ "telefone = ?, "
					+ "cpf = ? "
					+ "WHERE id = ?";
		}
		try {
			PreparedStatement ps = this.connectionFactory
					.getConexao().prepareStatement(sql);
			ps.setString(1, dto.getNome());
			ps.setString(2, dto.getRua());
			ps.setString(3, dto.getBairro());
			ps.setString(4, dto.getCep());
			ps.setInt(5, dto.getNumero());
			ps.setString(6, dto.getEstado());
			ps.setString(7, dto.getTelefone());
			ps.setString(8, dto.getCpf());
			if(!insert){
				ps.setLong(9, dto.getId());
				ps.executeUpdate();
			}else{
				ps.execute();
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caio.service.ClienteServiceInterface#excluir(java.lang.Long)
	 */
	public void excluir(Long id) {
		try{
			String sql = "delete FROM cliente WHERE id = ?";
			PreparedStatement statement = connectionFactory.getConexao()
					.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
			statement.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caio.service.ClienteServiceInterface#buscarTodos()
	 */
	public List<ClienteListaDTO> buscarTodos(String nome) {
		Boolean isNomePresent = Boolean.FALSE;
		String sql = "select * from cliente ";
		if (nome != null && !"".equals(nome.trim()) ){
			isNomePresent = Boolean.TRUE;
			sql+=" where nome like ? ";
		}
		List<ClienteListaDTO> retorno = new ArrayList<>();
		try {
			PreparedStatement ps = connectionFactory.getConexao().prepareStatement(sql);
			if(isNomePresent){
				ps.setString(1,"%"+nome+"%");
			}
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ClienteListaDTO c1 = new ClienteListaDTO();
				c1.setId(rs.getLong(1));
				c1.setNome(rs.getString(2));
				retorno.add(c1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.caio.service.ClienteServiceInterface#buscarCliente(java.lang.Long)
	 */
	public ClienteDTO buscarCliente(Long id) {
		if (id == null){
			return null;
		}
		ClienteDTO clienteDTO = null;
		try {
			String sql = "SELECT * FROM cliente WHERE id = ?";
			PreparedStatement statement = connectionFactory.getConexao()
					.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				clienteDTO = new ClienteDTO();
				clienteDTO.setId(resultSet.getLong("id"));
				clienteDTO.setNome(resultSet.getString("nome"));
				clienteDTO.setRua(resultSet.getString("rua"));
				clienteDTO.setBairro(resultSet.getString("bairro"));
				clienteDTO.setCep(resultSet.getString("cep"));
				clienteDTO.setNumero(resultSet.getInt("numero"));
				clienteDTO.setEstado(resultSet.getString("estado"));
				clienteDTO.setTelefone(resultSet.getString("telefone"));
				clienteDTO.setIdade(resultSet.getInt("idade"));
				clienteDTO.setCpf(resultSet.getString("cpf"));
			}
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clienteDTO;
	}

}
