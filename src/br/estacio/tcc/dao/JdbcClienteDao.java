package br.estacio.tcc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import br.estacio.tcc.modelo.Cliente;
import br.estacio.tcc.modelo.Endereco;
import br.estacio.tcc.modelo.Usuario;

@Repository
public class JdbcClienteDao {

	private JdbcTemplate jdbcTemplate;
	private Connection connection;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void adiciona(Cliente c) {

		this.jdbcTemplate
				.update("insert into clientes " + "(nome, email, telefone, cep, logradouro, numero, complemento, "
						+ "referencia, bairro, cidade, estado, cpf, cnpj)"

						+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)",

						c.getNome(), c.getEmail(), c.getTelefone(), c.getEndereco().getCep(),
						c.getEndereco().getLogradouro(), c.getEndereco().getNumero(), c.getEndereco().getComplemento(),
						c.getEndereco().getReferencia(), c.getEndereco().getBairro(), c.getEndereco().getCidade(),
						c.getEndereco().getEstado(), c.getCpf(), c.getCnpj());
	}

	public List<Cliente> lista() {
		// TODO Auto-generated method stub

		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from clientes");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// adiciona a tarefa na lista
				clientes.add(populaCliente(rs));
			}

			rs.close();
			stmt.close();

			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Cliente populaCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();

		// monta o endereço
		endereco.setLogradouro(rs.getString("logradouro"));
		endereco.setNumero(rs.getString("numero"));
		endereco.setComplemento(rs.getString("complemento"));
		endereco.setCep(rs.getString("cep"));
		endereco.setReferencia(rs.getString("referencia"));
		endereco.setBairro(rs.getString("bairro"));
		endereco.setCidade(rs.getString("cidade"));
		endereco.setEstado(rs.getString("estado"));

		// popula o objeto tarefa
		cliente.setId(rs.getLong("id"));
		cliente.setNome(rs.getString("nome"));
		cliente.setEmail(rs.getString("email"));
		cliente.setTelefone(rs.getString("telefone"));
		cliente.setEndereco(endereco);
		cliente.setCpf(rs.getString("cpf"));
		cliente.setCnpj(rs.getString("cnpj"));

		return cliente;
	}

	public Cliente buscaClientePorId(Long id) {
		// TODO Auto-generated method stub

		try {
			Cliente cliente = new Cliente();
			PreparedStatement stmt = this.connection.prepareStatement("select * from clientes where id=? limit 1;");
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// adiciona a tarefa na lista
				cliente = populaCliente(rs);
			}

			rs.close();
			stmt.close();

			return cliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Cliente> buscaClientePorNome(String nome){

		System.out.println("cliente "+ nome +" recebido. fazendo busca...");
		String sql="select * from clientes where nome like ? ";
		return this.jdbcTemplate.query(sql,new String[]{"%"+nome+"%"}, new ClienteMapper());
	
	}

	public void atualiza(Cliente c) {
		String sql = "update clientes " + "set nome=?," + "email=?," + "telefone=?," + "cep=?," + "logradouro=?,"
				+ "numero=?," + "complemento=?," + "referencia=?," + "bairro=?," + "cidade=?," + "estado=?," + "cpf=?,"
				+ "cnpj=? where id=?";

		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getEmail());
			stmt.setString(3, c.getTelefone());
			stmt.setString(4, c.getEndereco().getCep());
			stmt.setString(5, c.getEndereco().getLogradouro());
			stmt.setString(6, c.getEndereco().getNumero());
			stmt.setString(7, c.getEndereco().getComplemento());
			stmt.setString(8, c.getEndereco().getReferencia());
			stmt.setString(9, c.getEndereco().getBairro());
			stmt.setString(10, c.getEndereco().getCidade());
			stmt.setString(11, c.getEndereco().getEstado());
			stmt.setString(12, c.getCpf());
			stmt.setString(13, c.getCnpj());
			stmt.setLong(14, c.getId());
			System.out.println(stmt);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	private static final class ClienteMapper implements RowMapper<Cliente> {

	    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Cliente cliente = new Cliente();
	        cliente.setEndereco(new Endereco());
	        cliente.setId(rs.getLong("id"));
	        cliente.setNome(rs.getString("nome"));
	        cliente.setEmail(rs.getString("email"));
	        cliente.setTelefone(rs.getString("telefone"));
	        cliente.setCnpj(rs.getString("cnpj"));
	        cliente.setCpf(rs.getString("cpf"));
	        cliente.setCpf(rs.getString("cep"));
	        cliente.getEndereco().setLogradouro(rs.getString("logradouro"));
	        cliente.getEndereco().setNumero(rs.getString("numero"));
	        cliente.getEndereco().setComplemento(rs.getString("complemento"));
	        cliente.getEndereco().setReferencia(rs.getString("referencia"));
	        cliente.getEndereco().setBairro(rs.getString("bairro"));
	        cliente.getEndereco().setCidade(rs.getString("cidade"));
	        cliente.getEndereco().setEstado(rs.getString("estado"));
	        return cliente;
	    }
	}

}
