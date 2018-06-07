package br.com.caio.model;

import br.com.caio.dto.ClienteDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private ConnectionFactory conexaoComBanco;


    /**
     *
     CREATE TABLE `cliente` (
     `id` int NOT NULL AUTO_INCREMENT,
     `nome` varchar(255) NOT NULL,
     PRIMARY KEY (`id`),
     UNIQUE KEY `cliente_id_UNIQUE` (`id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1
     * @throws Exception 

     */

    public ClienteDAO(ConnectionFactory conexaoComBanco) throws Exception {
    	this.conexaoComBanco = conexaoComBanco;
    }

    public static void main(String[] args) throws Exception {
    	ConnectionFactory conexao = new ConnectionFactory("root","1234","jdbc:mysql://localhost:3306/caio_cliente?useTimezone=true&serverTimezone=UTC");
        ClienteDAO cliente = new ClienteDAO(conexao);
        cliente.insert(new ClienteDTO(null,"CAIO"));
        conexao.desconectar();
    }

    public boolean insert(ClienteDTO cliente) throws Exception {
        String sql = "INSERT INTO cliente (nome) VALUES (?)";
        PreparedStatement statement = conexaoComBanco.getConexao().prepareStatement(sql);
        statement.setString(1, cliente.getNome());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        return rowInserted;
    }

    public List<ClienteDTO> listAll() throws Exception {
        List<ClienteDTO> listRet = new ArrayList<>();

        String sql = "SELECT * FROM cliente";
        Statement statement = conexaoComBanco.getConexao().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String nome = resultSet.getString("nome");

            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(id);
            clienteDTO.setNome(nome);
            //TODO implementar o resto
            listRet.add(clienteDTO);
        }

        resultSet.close();
        statement.close();

        return listRet;
    }

    public boolean deleteBook(ClienteDTO clienteDTO) throws Exception {
        String sql = "DELETE FROM cliente where id = ?";

        PreparedStatement statement = conexaoComBanco.getConexao().prepareStatement(sql);
        statement.setLong(1, clienteDTO.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        return rowDeleted;
    }

    public boolean update(ClienteDTO clienteDTO) throws Exception {
        String sql = "UPDATE cliente SET nome = ?";
        sql += " WHERE id = ?";

        PreparedStatement statement = conexaoComBanco.getConexao().prepareStatement(sql);
        statement.setString(1, clienteDTO.getNome());
        statement.setLong(2, clienteDTO.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        return rowUpdated;
    }

    public ClienteDTO getCliente(Long id) throws Exception {
        ClienteDTO clienteDTO = null;
        String sql = "SELECT * FROM cliente WHERE id = ?";

        PreparedStatement statement = conexaoComBanco.getConexao().prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nome = resultSet.getString("nome");
            clienteDTO = new ClienteDTO(id, nome);
        }

        resultSet.close();
        statement.close();

        return clienteDTO;
    }
}