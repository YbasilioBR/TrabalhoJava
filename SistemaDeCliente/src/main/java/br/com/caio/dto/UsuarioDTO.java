package br.com.caio.dto;

public class UsuarioDTO {

	private String login;
	private String senha;
	private long id;
	private boolean acessocliente;
	private boolean acessousuario;
	
	public UsuarioDTO() {
	}
	
	public UsuarioDTO(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getAcessocliente() {
		return acessocliente;
	}

	public void setAcessocliente(boolean acessocliente) {
		this.acessocliente = acessocliente;
	}

	public boolean getAcessousuario() {
		return acessousuario;
	}

	public void setAcessousuario(boolean acessousuario) {
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
