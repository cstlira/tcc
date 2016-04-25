package br.estacio.tcc.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value=WebApplicationContext.SCOPE_SESSION)
public class Coleta {
	
	private String idCliente;

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	private Map<String, List<Produto>> itensParaColeta = new HashMap<>();

	public Map<String, List<Produto>> getItensParaColeta() {
		return itensParaColeta;
	}

	public void setItensParaColeta(Map<String, List<Produto>> itensParaColeta) {
		this.itensParaColeta = itensParaColeta;
	}


	public void addProduto(String idCliente, Produto produto) {
		if (itensParaColeta.containsKey(idCliente)) {
			List<Produto> lista = getProdutos(idCliente);
			for(Produto prod: lista) {
				if(prod.getId()==produto.getId()) {
					int qtd = prod.getQuantidade();
					lista.remove(prod);
					produto.setQuantidade(produto.getQuantidade()+qtd);
					lista.add(produto);
					return;
				}
			}
			lista.add(produto);
			itensParaColeta.put(idCliente, lista);
		} else {
			List<Produto> lista = getProdutos(idCliente);
			lista.add(produto);
			itensParaColeta.put(idCliente, lista);
		}
	}

	public void limpaColeta() {
		itensParaColeta.clear();
	}

	public String toString() {
		return itensParaColeta.toString();
	}
	
	public List<Produto> getProdutos(String idCliente){
		if (itensParaColeta.get(idCliente)!=null) {
		return itensParaColeta.get(idCliente);
		} else {
		return new ArrayList<Produto>();
		}
	}
	

}
