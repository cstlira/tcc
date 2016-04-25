package br.estacio.tcc.modelo;

import java.util.Date;

public class Agendamento {
	
	private Long id;
	private Date data;
	private Cliente cliente;
	private String descricao;
	private boolean efetuada;



	public Agendamento() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isEfetuada() {
		return efetuada;
	}
	public void setEfetuada(boolean efetuada) {
		this.efetuada = efetuada;
	}
	
	
	
	

}


