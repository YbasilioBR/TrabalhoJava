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
	
	private JPanel criaPainelCampo(String label, JTextField campo) {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 2));
		panel1.add(new JLabel(label));
		panel1.add(campo);
		return panel1;
	}	
	
	private JPanel desenhaPanelBotoes() {
		JPanel panelLogin = new JPanel();
	
		campoLogin = new JTextField("");
		panelLogin.add(criaPainelCampo("Login:", campoLogin),BorderLayout.NORTH);
		
		campoSenha = new JTextField("");		
		panelLogin.add(criaPainelCampo("Senha:", campoSenha),BorderLayout.SOUTH);
		
		return panelLogin;		
	}
	
	
	
	private void desenhaTela() throws Exception {
		janela.setLayout(new GridLayout(3,0));
		janela.getContentPane().add(desenhaPanelBotoes(), BorderLayout.CENTER);

		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
		janela.setSize(300, 300);
	}

	public LoginView() throws Exception {
		desenhaTela();
	}

}
