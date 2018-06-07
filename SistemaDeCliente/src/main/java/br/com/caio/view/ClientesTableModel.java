package br.com.caio.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.caio.dto.ClienteListaDTO;

public class ClientesTableModel extends AbstractTableModel {

	private final List<ClienteListaDTO> clientes;

	public ClientesTableModel(List<ClienteListaDTO> clientes) {
		this.clientes = clientes;
	}

	public int getColumnCount() {
		return 1;
	}

	public int getRowCount() {
		return clientes.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex == -1){
			return null;
		}
		ClienteListaDTO n = clientes.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return n.getNome();
		case 1:
			return n.getId();
		}
		return null;
	}
}