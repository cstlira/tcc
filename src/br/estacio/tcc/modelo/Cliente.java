package br.estacio.tcc.modelo;

import java.sql.Timestamp;

public class Cliente {

	private String id;
	private String nome;
	private String telefone;
	private Endereco endereco;
	private String email;
	private String cnpj;
	private String cpf;
	private Timestamp dataCadastro;
	private Boolean ativo;
	private String registro;

	
	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	
	public boolean isPessoaFisica(){
		if(this.cpf == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void reset(){
		this.id=null;
		this.nome=null;
		this.telefone=null;
		this.endereco=null;
		this.email=null;
		this.cnpj=null;
		this.cpf=null;
	}

}
