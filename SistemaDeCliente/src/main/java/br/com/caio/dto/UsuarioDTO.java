package br.com.caio.dto;

public class UsuarioDTO {

	private String login;
	private String senha;
	private int id;
	private int acessocliente;
	private int acessousuario;
	
	public UsuarioDTO() {
	}
	
	public UsuarioDTO(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcessocliente() {
		return acessocliente;
	}

	public void setAcessocliente(int acessocliente) {
		this.acessocliente = acessocliente;
	}

	public int getAcessousuario() {
		return acessousuario;
	}

	public void setAcessousuario(int acessousuario) {
		this.acessousuario = acessousuario;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
