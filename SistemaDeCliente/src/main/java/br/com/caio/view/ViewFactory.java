package br.com.caio.view;

public class ViewFactory {

	public static ClienteViewInterface criaClienteView() throws Exception{
		return new ClienteView();
	}
	
	public static LoginViewInterface criaLoginView() throws Exception{
		return new LoginView();
	}
	
	
}
