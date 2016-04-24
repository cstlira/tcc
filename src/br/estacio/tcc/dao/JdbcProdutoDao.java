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
import br.estacio.tcc.modelo.Produto;

@Repository
public class JdbcProdutoDao {

	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}



	public List<Produto> listaProdutos(){
		String sql="select * from produtos order by descricao";
		return this.jdbcTemplate.query(sql, new ProdutoMapper());
	}

	
	public Produto buscaProdutoPorId(Long id){
		try {
		String sql="select * from produtos where id=?";
		return this.jdbcTemplate.queryForObject(sql, new Long[] {id}, new ProdutoMapper()); }
		catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	private static final class ProdutoMapper implements RowMapper<Produto> {
	    public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Produto produto = new Produto();
	        produto.setId(rs.getLong("id"));
	        produto.setDescricao(rs.getString("descricao"));
	        produto.setPeso(rs.getDouble("peso"));
	        return produto;
	    }
	}

}
