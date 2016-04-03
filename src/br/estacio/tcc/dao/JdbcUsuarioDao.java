package br.estacio.tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.estacio.tcc.modelo.Usuario;

@Repository
public class JdbcUsuarioDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public Usuario loginUser(String login,  String password) {
		
		Usuario user = this.findUserByLogin(login);
		
		if(user != null && user.getPassword().equals(password))  {
			return user;
		}
		return null;
	}
	
	
	
	public Usuario findUserByLogin(String login) {
		
		
		try{
		Usuario user = this.jdbcTemplate.queryForObject(
		        "select * from usuarios where login=?",
		        new Object[]{login},
		        new RowMapper<Usuario>() {
		            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		        		Usuario user = new Usuario();
		            	user.setId(rs.getLong("id"));
		            	user.setNome(rs.getString("nome"));
		            	user.setLogin(rs.getString("login"));
		            	user.setPassword(rs.getString("password"));
		            	user.setNivel(rs.getInt("nivel"));
		            	user.setSenhaexpirada(rs.getBoolean("senhaexpirada"));

						return user;
						

						
		            }});
						return user;	
						} catch (EmptyResultDataAccessException e) {
								return null;
						}
						}
	

	public Usuario getUsuario(Usuario usuario) { 
		
		if(usuario == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo");
		}


				Usuario user = this.jdbcTemplate.queryForObject(
						
		        "select * from usuarios where login=? and password=?",
		        
		        new Object[]{usuario.getLogin(),usuario.getPassword()},
		        new RowMapper<Usuario>() {
		            public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		        		Usuario user = new Usuario();
		            	if(rs.next()){
		            	user.setId(rs.getLong("id"));
		            	user.setLogin(rs.getString("login"));
		            	user.setPassword(rs.getString("password"));
		            	user.setNivel(rs.getInt("nivel"));
		            	user.setSenhaexpirada(rs.getBoolean("senhaexpirada"));
						return user;
		            }
						return user;}
						
		        });
				return user;
				
	
} }

