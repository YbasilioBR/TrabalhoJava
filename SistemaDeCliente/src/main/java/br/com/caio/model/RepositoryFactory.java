package br.com.caio.model;

public class RepositoryFactory {

	public static ClienteRepositoryInterface criaClienteRepository(){
		
		ConnectionFactory fabrica = new ConnectionFactory(
				"root", 
				"1234", 
				"jdbc:mysql://192.168.10.4:3306/aulas_sistema_cliente"
				+ "?useTimezone=true&serverTimezone=UTC");
		
		return new ClienteRepository(fabrica);
	}
	
}
