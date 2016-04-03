package br.estacio.tcc.modelo;


public class Usuario {
	private String login, password, nome;
	private int nivel;
	private boolean senhaExpirada;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public boolean isSenhaexpirada() {
		return senhaExpirada;
	}

	public void setSenhaexpirada(boolean senhaexpirada) {
		this.senhaExpirada = senhaexpirada;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
