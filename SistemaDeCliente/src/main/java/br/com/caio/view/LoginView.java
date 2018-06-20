package br.com.caio.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.caio.dto.ClienteDTO;
import br.com.caio.model.RepositoryFactory;
import br.com.caio.service.UsuarioServiceInterface;
import br.com.caio.service.ServiceFactory;

public class LoginView implements LoginViewInterface {
	
	private UsuarioServiceInterface service = ServiceFactory
			.criaUsuarioService(RepositoryFactory.criaUsuarioRepository());
	
	JFrame janela = new JFrame("Login");

	JTable table = new JTable();

	private JTextField campoLogin;	
	private JTextField campoSenha;
	private JLabel textoLogin;
	private JLabel textoSenha;
	private JButton botaoLogar;
	
	/*private JPanel criaPainelCampo(String label, JTextField campo) {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 2));
		panel1.add(new JLabel(label));
		panel1.add(campo);
		return panel1;
	}	*/
	
	private JPanel desenhaPanelBotoes() {
		JPanel panelLogin = new JPanel(new GridLayout(3,1));

		textoLogin = new JLabel("Login");
		panelLogin.add(textoLogin);
		
		campoLogin = new JTextField("");
		panelLogin.add(campoLogin);
		
		textoSenha = new JLabel("Senha");
		panelLogin.add(textoSenha);
		
		campoSenha = new JTextField("");		
		panelLogin.add(campoSenha);
		
		botaoLogar = new JButton("Entrar");
		panelLogin.add(botaoLogar,BorderLayout.CENTER);
		
		return panelLogin;		
	}
	
	
	
	private void desenhaTela() throws Exception {

		janela.getContentPane().add(desenhaPanelBotoes(), BorderLayout.CENTER);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
		janela.setSize(300, 120);
	}

	public LoginView() throws Exception {
		desenhaTela();
	}

}
