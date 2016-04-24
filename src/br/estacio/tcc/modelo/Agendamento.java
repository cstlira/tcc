package br.estacio.tcc.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.AutoPopulatingList;

public class Agendamento {
	
	private Long id;
	private Date data;
	private Cliente cliente;
	private String descricao;
	private boolean efetuada;
	private ItemColeta itemColeta;
	private int numItens;
	private List<ItemColeta> itensColetados;
	
	public List<ItemColeta> getItensColetados() {
		return itensColetados;
	}


	public void setItensColetados(List<ItemColeta> itensColetados) {
		this.itensColetados = itensColetados;
	}


	public Agendamento() {
	}
	
	
	public ItemColeta getItemColeta() {
		return itemColeta;
	}

	public void setItemColeta(ItemColeta itemColeta) {
		this.itemColeta = itemColeta;
	}
	
	public int getNumItens() {
		return numItens;
	}

	public void setNumItens(int numItens) {
		this.numItens = numItens;
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


