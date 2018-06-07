package br.com.caio.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
import br.com.caio.dto.ClienteListaDTO;
import br.com.caio.model.RepositoryFactory;
import br.com.caio.service.ClienteService;
import br.com.caio.service.ClienteServiceInterface;
import br.com.caio.service.ServiceFactory;

public class ClienteView implements ClienteViewInterface {

	private ClienteServiceInterface service = ServiceFactory
			.criaClienteService(RepositoryFactory.criaClienteRepository());

	JFrame janela = new JFrame("Cadastro de Cliente");

	JTable table = new JTable();

	private JTextField campoBusca;
	
	private JTextField campoNome;
	private JFormattedTextField campoTelefone;
	private JTextField campoEndereco;
	private JTextField campoNumero;
	private JTextField campoComplemento;
	private JTextField campoBairro;
	private JTextField campoCidade;
	private JTextField campoEstado;
	private JFormattedTextField campoCep;
	private JTextField campoCPF;
	private Long idAtual = null;

	private JPanel desenhaPanelLista() {
		JPanel panelLista = new JPanel();
		table.setBorder(new LineBorder(Color.black));
		table.setGridColor(Color.black);
		table.setShowGrid(true);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				ClienteDTO cliente = service
						.buscarCliente((Long) table.getModel().getValueAt(table.getSelectedRow(), 1));
				toCampos(cliente);
			}
		});

		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(table);
		scroll.setSize(100, 100);

		campoBusca = new JTextField("");
		
		campoBusca.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		        JTextField textField = (JTextField) e.getSource();
		        String text = textField.getText();
		        atualizaTabelaLista(text);
		      }

		      public void keyTyped(KeyEvent e) {
		      }

		      public void keyPressed(KeyEvent e) {
		      }
		    });
		
		atualizaTabelaLista();
		panelLista.add(scroll);
		
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		panelTop.add(campoBusca,BorderLayout.NORTH);
		panelTop.add(panelLista,BorderLayout.CENTER);
		return panelTop;
	}

	
	private void atualizaTabelaLista() {
		this.atualizaTabelaLista(campoBusca.getText());
	}
	private void atualizaTabelaLista(String nome) {
		table.setModel(new ClientesTableModel(service.buscarTodos(nome)));
	}

	private JPanel desenhaPanelBotoes() {
		JPanel panelBotoes = new JPanel();
		JButton btnSalvar = new JButton("Salvar");
		JButton btnExcluir = new JButton("Excluir");
		JButton btnLimpar = new JButton("Limpar");

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				service.salvar(toDTO());
				atualizaTabelaLista();
			}
		});

		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				service.excluir(idAtual);
				table.clearSelection();
				JOptionPane.showMessageDialog(null, "Dado excluido com sucesso !");
				limpaCampos();
				atualizaTabelaLista();
			}
		});

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
		this.campoNome.setText("");
		this.campoTelefone.setText("");
		this.campoEndereco.setText("");
		this.campoNumero.setText("");
		this.campoComplemento.setText("");
		this.campoBairro.setText("");
		this.campoCep.setText("");
		this.campoCidade.setText("");
		this.campoEstado.setText("");
		this.campoCPF.setText("");
		idAtual = null;
	}

	private void toCampos(ClienteDTO dto) {
		if (dto != null) {
			idAtual = dto.getId();
			this.campoNome.setText(dto.getNome());
			this.campoTelefone.setText(dto.getTelefone());
			this.campoEndereco.setText(dto.getRua());
			this.campoNumero.setText(dto.getNumero().toString());
			this.campoComplemento.setText("");
			this.campoBairro.setText(dto.getBairro());
			this.campoCep.setText(dto.getCep());
			// this.campoCidade.setText(dto.get);
			this.campoEstado.setText(dto.getEstado());
			this.campoCPF.setText(dto.getCpf());
		}
	}

	private ClienteDTO toDTO() {
		ClienteDTO dto = new ClienteDTO();
		dto.setId(idAtual);
		dto.setNome(this.campoNome.getText());
		dto.setTelefone(this.campoTelefone.getText());
		dto.setRua(this.campoEndereco.getText());
		try {
			dto.setNumero(Integer.valueOf(this.campoNumero.getText()));
		} catch (Exception e) {
			dto.setNumero(0);
		}
		// dto.set(this.campoComplemento.getText());
		dto.setBairro(this.campoBairro.getText());
		dto.setCep(this.campoCep.getText());
		// dto.setCidade(this.campoCidade.getText());
		dto.setEstado(this.campoEstado.getText());
		dto.setCpf(this.campoCPF.getText());
		return dto;
	}

	private JPanel desenhaPanelCadastro() throws ParseException {
		JPanel panelCadastro = new JPanel();
		panelCadastro.setLayout(new GridLayout(5, 2));

		campoNome = new JTextField("");
		panelCadastro.add(criaPainelCampo("Nome:", campoNome));

		campoTelefone = new JFormattedTextField("");
		(new MaskFormatter("(##)####-####")).install(campoTelefone);
		panelCadastro.add(criaPainelCampo("Telefone:", campoTelefone));

		campoEndereco = new JTextField("");
		panelCadastro.add(criaPainelCampo("Endereço:", campoEndereco));

		campoNumero = new JTextField("");
		panelCadastro.add(criaPainelCampo("Nº:", campoNumero));

		campoComplemento = new JTextField("");
		panelCadastro.add(criaPainelCampo("Complemento:", campoComplemento));

		campoBairro = new JTextField("");
		panelCadastro.add(criaPainelCampo("Bairro:", campoBairro));

		campoCep = new JFormattedTextField("");
		(new MaskFormatter("#####-###")).install(campoCep);
		panelCadastro.add(criaPainelCampo("Cep:", campoCep));

		campoCidade = new JTextField("");
		panelCadastro.add(criaPainelCampo("Cidade:", campoCidade));

		campoEstado = new JTextField("");
		panelCadastro.add(criaPainelCampo("Estado:", campoEstado));

		campoCPF = new JTextField("");
		panelCadastro.add(criaPainelCampo("CPF:", campoCPF));

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

	public ClienteView() throws Exception {
		desenhaTela();
	}

}
