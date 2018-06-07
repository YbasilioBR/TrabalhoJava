package br.com.caio.view;

public class ViewFactory {

	public static ClienteViewInterface criaClienteView() throws Exception{
		return new ClienteView();
	}
	
}
