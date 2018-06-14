package br.com.caio.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caio.dto.UsuarioDTO;


public class UsuarioRepository implements UsuarioRepositoryInterface {

	private ConnectionFactory connectionFactory;

	public UsuarioRepository(ConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
	}
	
	public UsuarioDTO buscarUsuario(String login, String senha) {
		
		UsuarioDTO usuarioDTO = null;
		try {
			String sql = "SELECT * FROM usuario WHERE login = ? and senha = ?";
			PreparedStatement statement = connectionFactory.getConexao()
					.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, senha);	
			
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				usuarioDTO = new UsuarioDTO();
				usuarioDTO.setId(resultSet.getInt("id"));
				usuarioDTO.setLogin(resultSet.getString("nome"));
				usuarioDTO.setSenha(resultSet.getString("senha"));
				usuarioDTO.setAcessocliente(resultSet.getInt("acessocliente"));
				usuarioDTO.setAcessousuario(resultSet.getInt("acessousuario"));
			}
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuarioDTO;
	}
}
