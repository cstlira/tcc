package br.estacio.tcc.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.estacio.tcc.dao.JdbcClienteDao;
import br.estacio.tcc.dao.JdbcProdutoDao;
import br.estacio.tcc.modelo.Agendamento;
import br.estacio.tcc.modelo.Cliente;
import br.estacio.tcc.modelo.ItemColeta;
import br.estacio.tcc.modelo.Produto;

@Controller
@RequestMapping("coleta")
public class ColetaController {

	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	JdbcClienteDao clienteDao;
	
	@Autowired
	JdbcProdutoDao produtoDao;
	
	@RequestMapping(value = "agenda/{id}", method=RequestMethod.GET)
	public String agendaColeta(Model model, @PathVariable String id){
		model.addAttribute("cliente", clienteDao.buscaClientePorId(id));
		model.addAttribute("agendamento", new Agendamento());
		model.addAttribute("numItens",0);
		
		Map<String,String> listaProdutos = new LinkedHashMap<String,String>();
		for(Produto produto : produtoDao.listaProdutos()) {
			listaProdutos.put(String.valueOf(produto.getId()), produto.getDescricao());
		}
		
		model.addAttribute("listaProdutos",listaProdutos); 
		return "coleta/agendamento";
	}
	
	
	@RequestMapping(value = "agenda", method=RequestMethod.POST)
	public String agendaColeta(Model model, Cliente cliente, Agendamento agendamento){
		
		System.out.println("Mostrando itens...");
		return "redirect:agenda/"+agendamento.getCliente().getId();
	}
	
	
	
	
	@RequestMapping(value = "agenda/addItemColeta", method=RequestMethod.POST)
	public String addItem(Model model, Agendamento agendamento, RedirectAttributes redirectAttributes, HttpSession session){
		
			//Retira o item do objeto Agendamento com ID do Produto a ser coletado e quantidade.
			ItemColeta itemColeta = agendamento.getItemColeta();

			//Calcula o pesoTotal e configura no objeto.
			
			//Busca o produto no banco de dados para saber o peso unitário.
			Produto produto = produtoDao.buscaProdutoPorId(itemColeta.getIdProduto());
			
			//Multiplica o peso unitário do produto pela quantidade a ser coletada do mesmo e configura no item
			BigDecimal pesoTotal = new BigDecimal(produto.getPeso()*itemColeta.getQuantidade()).setScale(2, RoundingMode.HALF_EVEN);
			itemColeta.setPeso(pesoTotal.doubleValue());
			
			//Seta ID do cliente
			itemColeta.setIdCliente(agendamento.getCliente().getId());
			
			//Seta Descricao
			itemColeta.setDescricao(produto.getDescricao());
			//Volta o objeto agendamento para o form com o arraylist incrementado.

			if(session.getAttribute("itensAdicionados")==null){
			List<ItemColeta> itensAdicionados = new ArrayList<ItemColeta>();
			itensAdicionados.add(itemColeta);
			session.setAttribute("itensAdicionados", itensAdicionados);
			} 
			
			else {
			@SuppressWarnings("unchecked")
			List<ItemColeta> itensAdicionados = (List<ItemColeta>) session.getAttribute("itensAdicionados");
			for(int i = 0; i<itensAdicionados.size(); i++){
				ItemColeta item = itensAdicionados.get(i);
				if(item.getIdProduto()==itemColeta.getIdProduto()) {
					itensAdicionados.remove(i);
				}
			}
			itensAdicionados.add(itemColeta);
			session.setAttribute("itensAdicionados", itensAdicionados);
			agendamento.setItensColetados(itensAdicionados);
			}
			
			redirectAttributes.addFlashAttribute("agendamento",agendamento);
			
			return "redirect:"+agendamento.getCliente().getId();
	}
	
}
