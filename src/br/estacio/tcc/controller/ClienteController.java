package br.estacio.tcc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.estacio.tcc.dao.JdbcClienteDao;
import br.estacio.tcc.modelo.Cliente;
import br.estacio.tcc.validator.BuscaClienteValidator;

@Controller
public class ClienteController {

	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	JdbcClienteDao dao;
	
	@Autowired
	BuscaClienteValidator buscaClienteValidator;

	
	/* Pagina Principal */
	@RequestMapping(value = "Home")
	public String listaMenu(Model model) {
		return "home";
	}
	
	
	@RequestMapping(value = "CadastroCliente", method = RequestMethod.GET)
	public String formCadastro(Model model, Cliente c) {
		model.addAttribute("cliente", c);
		return "cliente/home";
	}
	
	
	/* CADASTRO DE CLIENTES */
	
	@RequestMapping(value = "CadastroCliente", method = RequestMethod.POST)
	public String formCadastroSubmit(Model model, Cliente c, RedirectAttributes redir, BindingResult bindingResult) {
	
		
		if(c.isPessoaFisica() && dao.buscaClientePorCPF(c.getCpf())!=null) {
				redir.addFlashAttribute("msgFalha","Erro ao adicionar cliente. CPF já existe.");
				redir.addFlashAttribute(c);
				return "redirect:CadastroCliente?tipoCliente=pf";
				}
		
		if((!c.isPessoaFisica()) && dao.buscaClientePorCNPJ(c.getCnpj())!=null)  {
				redir.addFlashAttribute("msgFalha","Erro ao adicionar cliente. CNPJ já existe.");
				redir.addFlashAttribute(c);
				return "redirect:CadastroCliente?tipoCliente=pj";
			}
		
		dao.adiciona(c);
		redir.addFlashAttribute("msgSucesso","Cliente adicionado com sucesso.");
		return "redirect:CadastroCliente";
		}
	
	

	/* BUSCA DE CLIENTES */
	
	@RequestMapping(value = "BuscaCliente", method = RequestMethod.POST)
	public String buscaCliente(Model model,@Valid Cliente c, BindingResult bindingResult) {
		String nome = c.getNome();
		c.reset();
		if(bindingResult.hasErrors()){
			return "cliente/home";
		}
		List<Cliente> clientes = dao.buscaClientePorNome(nome);
		if(clientes.isEmpty()){
			bindingResult.rejectValue("nome","nome.notfound","Cliente(s) não encontrado(s).");
			return "cliente/home";
		}
		model.addAttribute("clientesEncontrados",clientes);
		return "cliente/lista_clientes_encontrados";
	}

	
	@RequestMapping(value = "BuscaCliente", method = RequestMethod.GET)
	public String buscaCliente(Model model, Cliente c, RedirectAttributes redir) {
		model.addAttribute("cliente",c);
		return "cliente/form_busca_cliente"; 
	}
	
	
	
	@RequestMapping(value = "MostraCliente", method = RequestMethod.GET)
	public String mostrarCliente(Model model, Cliente c, @RequestParam Long id) {
		Cliente cliente = dao.buscaClientePorId(id);
		model.addAttribute("cliente",cliente);
		return "cliente/mostra_cliente"; 
	}
	
	@RequestMapping(value = "AtualizaCliente", method = RequestMethod.POST)
	public String atualizaCliente(Model model, Cliente c, RedirectAttributes redirectAttributes) {
		dao.atualiza(c);
		redirectAttributes.addFlashAttribute("msgClienteAtualizado","Cliente atualizado com sucesso!");
		return "redirect:MostraCliente?id="+c.getId(); 
	}


	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(buscaClienteValidator);
	}
	

	@ExceptionHandler(Exception.class)
	public ModelAndView errorHandler(HttpServletRequest req, Exception exception) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}

}
