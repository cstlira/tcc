package br.estacio.tcc.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.estacio.tcc.dao.JdbcClienteDao;
import br.estacio.tcc.dao.JdbcProdutoDao;
import br.estacio.tcc.modelo.Agendamento;
import br.estacio.tcc.modelo.Cliente;
import br.estacio.tcc.modelo.Coleta;
import br.estacio.tcc.modelo.Produto;

@Controller
@RequestMapping("coleta")
public class ColetaController {

	private static final Logger logger = LoggerFactory.getLogger(ColetaController.class);
	
	@Autowired
	JdbcClienteDao clienteDao;
	
	@Autowired
	JdbcProdutoDao produtoDao;
	
	@Autowired
	Coleta coleta;
	
	@RequestMapping(value = "agenda/{id}", method=RequestMethod.GET)
	public String agendaColeta(Model model, @PathVariable String id){
		Cliente cliente = clienteDao.buscaClientePorId(id);
		model.addAttribute("cliente", cliente);
		model.addAttribute("itensColeta", coleta.getProdutos(cliente.getId()));
		model.addAttribute("agendamento", new Agendamento());
		model.addAttribute("produto", new Produto());
		return "coleta/agendamento";
	}
	
	
	@RequestMapping(value = "agenda", method=RequestMethod.POST)
	public String agendaColeta(Model model, Cliente cliente, Agendamento agendamento){
		
		System.out.println("Mostrando itens...");
		return "redirect:agenda/"+agendamento.getCliente().getId();
	}
	
	
	
	
	@RequestMapping(value = "agenda/addProduto", method=RequestMethod.POST)
	public String addProduto(Model model, Produto produto, RedirectAttributes redirectAttributes){
		Produto p = produtoDao.buscaProdutoPorId(produto.getId());
		produto.setDescricao(p.getDescricao());
		BigDecimal peso = new BigDecimal(String.valueOf(p.getPeso())).setScale(2, BigDecimal.ROUND_HALF_UP);
		produto.setPeso(peso.doubleValue());
		coleta.addProduto(produto.getIdCliente(), produto);
		logger.debug("Adicionando produto à coleta: "+produto.getQuantidade()+" - "+produto.getDescricao());
		logger.debug("Cliente: "+produto.getIdCliente());
		logger.debug("Coleta: "+coleta);
		List<Produto> itensColeta = coleta.getProdutos(produto.getIdCliente());
		redirectAttributes.addFlashAttribute("itensColeta",itensColeta);
		return "redirect:"+produto.getIdCliente();
	}
	
	@ModelAttribute("listaProdutos")
	public Map<String,String> listaProdutos() {
		Map<String,String> listaProdutos = new HashMap<String,String>();
		for(Produto produto : produtoDao.listaProdutos()) {
			listaProdutos.put(String.valueOf(produto.getId()), produto.getDescricao());
		}
		return listaProdutos;
	}
	

}
