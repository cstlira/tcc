package br.estacio.tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.estacio.tcc.modelo.Cliente;
import br.estacio.tcc.modelo.Endereco;

@Repository
public class JdbcClienteDao {

	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void adiciona(Cliente c) {
		
		/* Gera ID p/ novo cliente */
		String id = UUID.randomUUID().toString().replace("-", "");
		while(this.buscaClientePorId(id)!=null){
		id = UUID.randomUUID().toString().replace("-", "");
		}
		
		this.jdbcTemplate
				.update("insert into clientes " + "(id, nome, email, telefone, cep, logradouro, numero, complemento, "
						+ "referencia, bairro, cidade, estado, cpf, cnpj)"

						+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",

						id, c.getNome(), c.getEmail(), c.getTelefone(), c.getEndereco().getCep(),
						c.getEndereco().getLogradouro(), c.getEndereco().getNumero(), c.getEndereco().getComplemento(),
						c.getEndereco().getReferencia(), c.getEndereco().getBairro(), c.getEndereco().getCidade(),
						c.getEndereco().getEstado(), c.getCpf(), c.getCnpj());
	}

	
	public List<Cliente> buscaTodosClientes(){
		String sql="select * from clientes order by nome";
		return this.jdbcTemplate.query(sql, new ClienteMapper());
	}

	public Cliente buscaClientePorId(String id){
		try {
		String sql="select * from clientes where id = ? ";
		return this.jdbcTemplate.queryForObject(sql,new String[]{id}, new ClienteMapper()); }
		catch (EmptyResultDataAccessException e){
			return null;
		}
	}
	
	
	public List<Cliente> buscaClientesPorNome(String nome){
		try {
		String sql="select * from clientes where nome like ? ";
		return this.jdbcTemplate.query(sql,new String[]{"%"+nome+"%"}, new ClienteMapper()); }
		catch (EmptyResultDataAccessException e){
			return null;
		}
	
	}
	
	public Cliente buscaClientePorCPFouCNPJ(String reg) {
		try {
		String sql="select * from clientes where cpf=? or cnpj=?";
		return this.jdbcTemplate.queryForObject(sql,new String[]{reg,reg}, new ClienteMapper());
		} catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	public void atualiza(Cliente c) {
		this.jdbcTemplate.update("update clientes set nome=?, email=?, telefone=?, cep=?, logradouro=?,"
				+ "numero=?, complemento=?, referencia=?, bairro=?, cidade=?, estado=?, cpf=?,"
				+ "cnpj=?, ativo=? where id=?",
				c.getNome(),
				c.getEmail(),
				c.getTelefone(),
				c.getEndereco().getCep(),
				c.getEndereco().getLogradouro(),
				c.getEndereco().getNumero(),
				c.getEndereco().getComplemento(),
				c.getEndereco().getReferencia(),
				c.getEndereco().getBairro(),
				c.getEndereco().getCidade(),
				c.getEndereco().getEstado(),
				c.getCpf(),
				c.getCnpj(),
				c.getAtivo(),
				c.getId());
	}
	
	
	
	private static final class ClienteMapper implements RowMapper<Cliente> {

	    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Cliente cliente = new Cliente();
	        cliente.setEndereco(new Endereco());
	        cliente.setId(rs.getString("id"));
	        cliente.setNome(rs.getString("nome"));
	        cliente.setEmail(rs.getString("email"));
	        cliente.setTelefone(rs.getString("telefone"));
	        cliente.setCnpj(rs.getString("cnpj"));
	        cliente.setCpf(rs.getString("cpf"));
	        cliente.setAtivo(rs.getBoolean("ativo"));
	        cliente.setDataCadastro(rs.getTimestamp("data_cadastro"));
	        cliente.getEndereco().setLogradouro(rs.getString("logradouro"));
	        cliente.getEndereco().setNumero(rs.getString("numero"));
	        cliente.getEndereco().setComplemento(rs.getString("complemento"));
	        cliente.getEndereco().setReferencia(rs.getString("referencia"));
	        cliente.getEndereco().setBairro(rs.getString("bairro"));
	        cliente.getEndereco().setCidade(rs.getString("cidade"));
	        cliente.getEndereco().setEstado(rs.getString("estado"));
	        cliente.getEndereco().setCep(rs.getString("cep"));
	        return cliente;
	    }
	}

}
