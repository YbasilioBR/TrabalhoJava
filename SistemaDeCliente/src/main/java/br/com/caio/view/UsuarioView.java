package br.com.caio.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import br.com.caio.dto.ClienteDTO;
import br.com.caio.dto.UsuarioDTO;
import br.com.caio.model.RepositoryFactory;
import br.com.caio.service.ServiceFactory;
import br.com.caio.service.UsuarioServiceInterface;

public class UsuarioView implements UsuarioViewInterface {

	private UsuarioServiceInterface service = ServiceFactory
			.criaUsuarioService(RepositoryFactory.criaUsuarioRepository());
	
	JFrame janela = new JFrame("Usuario");

	JTable table = new JTable();

	private JTextField campoLogin;	
	private JTextField campoSenha;
	private JTextField campoData;
	private JCheckBox chkUser;
	private JCheckBox chkCliente;
	private JTextField campoBusca;
	private long idAtual = 0;

	private JPanel desenhaPanelLista() {
		JPanel panelLista = new JPanel();
		table.setBorder(new LineBorder(Color.black));
		table.setGridColor(Color.black);
		table.setShowGrid(true);

		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(table);
		scroll.setSize(100, 100);

		campoBusca = new JTextField("");
		
		
		
	
		panelLista.add(scroll);
		
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		panelTop.add(campoBusca,BorderLayout.NORTH);
		panelTop.add(panelLista,BorderLayout.CENTER);
		return panelTop;
	}



	private JPanel desenhaPanelBotoes() {
		JPanel panelBotoes = new JPanel();
		JButton btnSalvar = new JButton("Salvar");
		JButton btnExcluir = new JButton("Excluir");
		JButton btnLimpar = new JButton("Limpar");

		btnLimpar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				limpaCampos();
			}
		});

		panelBotoes.add(btnSalvar);
		panelBotoes.add(btnExcluir);
		panelBotoes.add(btnLimpar);

		return panelBotoes;
	}

	private JPanel criaPainelCampo(String label, JTextField campo) {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 2));
		panel1.add(new JLabel(label));
		panel1.add(campo);
		return panel1;
	}
	
	private void limpaCampos() {
		this.campoLogin.setText("");
		this.campoSenha.setText("");
		this.campoData.setText("");
		this.chkUser.setEnabled(false);
		this.chkCliente.setEnabled(false);
		idAtual = 0;
	}
	
	private JPanel desenhaPanelCadastro() throws ParseException {
		JPanel panelCadastro = new JPanel();
		panelCadastro.setLayout(new GridLayout(5, 2));

		JLabel textoLogin = new JLabel("Login");
		panelCadastro.add(textoLogin);		
		campoLogin = new JTextField("");
		panelCadastro.add(campoLogin);
		
		JLabel textoSenha = new JLabel("Senha");
		panelCadastro.add(textoSenha);		
		campoSenha = new JTextField("");		
		panelCadastro.add(campoSenha);
		
		JLabel textoData = new JLabel("Data");
		panelCadastro.add(textoData);		
		campoData = new JTextField("");
		panelCadastro.add(campoData);
		
		JLabel textoUser = new JLabel("Acesso Usuario");
		panelCadastro.add(textoUser);		
		chkUser = new JCheckBox();		
		panelCadastro.add(chkUser);
		
		JLabel textoCliente = new JLabel("Acesso Usuario");
		panelCadastro.add(textoCliente);		
		chkCliente = new JCheckBox();		
		panelCadastro.add(chkCliente);

		return panelCadastro;
	}

	private void desenhaTela() throws Exception {
		// janela.setLayout(new GridLayout(3,0));
		janela.getContentPane().add(desenhaPanelLista(), BorderLayout.NORTH);
		janela.getContentPane().add(desenhaPanelCadastro(), BorderLayout.CENTER);
		janela.getContentPane().add(desenhaPanelBotoes(), BorderLayout.SOUTH);

		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setVisible(true);
		// janela.setSize(500, 500);
	}

	public UsuarioView() throws Exception {
		desenhaTela();
	}
	

	
}
