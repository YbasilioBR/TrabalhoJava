package br.com.caio.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private String jdbcUsuario;
	private String jdbcSenha;
	private String jdbcConexao;
	private Connection jdbcConnection;
	
	public ConnectionFactory(String jdbcUsuario, String jdbcSenha, String jdbcConexao) {
		super();
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcSenha = jdbcSenha;
		this.jdbcConexao = jdbcConexao;
	}
	
	
	public Connection getConexao() throws Exception{
		if (jdbcConnection == null || jdbcConnection.isClosed()){
			conectar();
		}
		return jdbcConnection;
	}
	
	private void conectar() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		jdbcConnection = DriverManager.getConnection(this.jdbcConexao, 
				this.jdbcUsuario, 
				this.jdbcSenha);
	}
	
	public void desconectar() throws Exception{
		if(jdbcConnection != null && !jdbcConnection.isClosed()){
			jdbcConnection.close();
		}
	}
	
}
