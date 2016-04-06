package br.estacio.tcc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.estacio.tcc.dao.JdbcClienteDao;
import br.estacio.tcc.modelo.Cliente;

@Controller
public class ClienteController {

	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	JdbcClienteDao dao;

	
	@RequestMapping(value = "cadastroCliente", method = RequestMethod.GET)
	public String formCadastro(Model model, Cliente c) {
		model.addAttribute("cliente", c);
		return "cliente/form_cliente";
	}
	
	
	
	@RequestMapping(value = "formCadastroCliente", method = RequestMethod.GET)
	public String returnFormCadastro(Model model, Cliente c, @RequestParam String tipoCliente) {
		model.addAttribute("cliente", c);
		if (tipoCliente.equals("pf")) {
			model.addAttribute("tipoCliente", tipoCliente);
			return "cliente/form_cadastro_pf";
		} else if (tipoCliente.equals("pj")) {
			model.addAttribute("tipoCliente", tipoCliente);
			return "cliente/form_cadastro_pj";
		}
		return null;
	}

	@RequestMapping(value = "buscaCliente", method = RequestMethod.POST)
	public String buscaCliente(Model model, Cliente c, BindingResult bindingResult) {
		List<Cliente> clientes = dao.buscaClientePorNome(c.getNome());
		if(clientes.isEmpty()){
			bindingResult.rejectValue("nome","nome.notfound","Cliente(s) não encontrado(s)."); 
			return "cliente/form_busca_cliente";
		}
		model.addAttribute("clientes",clientes);
		return "cliente/mostra_clientes";
	}

	@RequestMapping(value = "buscaCliente", method = RequestMethod.GET)
	public String formCadastroSubmit(Model model, Cliente c, RedirectAttributes redir) {
		model.addAttribute("cliente",c);
		return "cliente/form_busca_cliente";
	}
	
	
	
	
	/* LISTA MENU */
	@RequestMapping(value = "Home")
	public String listaMenu(Model model) {
		return "home";
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
