package br.estacio.tcc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value = "CadastroCliente", method = RequestMethod.GET)
	public String formCadastro(Model model, Cliente c, @RequestParam String tipoCliente) {
		model.addAttribute("cliente", c);

		if (tipoCliente.equals("pf")) {
			model.addAttribute("tipoCliente", tipoCliente);
		} else if (tipoCliente.equals("pj")) {
			model.addAttribute("tipoCliente", tipoCliente);
		}

		return "cliente/form_cadastro";
	}

	/* CADASTRA PESSOA FISICA. (ADICIONAR VALIDAÇÃO DOS DADOS) */
	@RequestMapping(value = "CadastroCliente", method = RequestMethod.POST)
	public String adicionaCliente(Model model, Cliente c, RedirectAttributes redir) {
		dao.adiciona(c);
	    redir.addFlashAttribute("cliente",c);
		return "redirect:/Home";
	}

	/* MOSTRA FORM PARA EDITAR CADASTRO DE PESSOA FISICA */
	
	@RequestMapping(value = "EditaCliente/{id}", method = RequestMethod.GET)
	public String edita(Model model, @PathVariable Long id) throws Exception {
		System.out.println(id);
		Cliente cliente = dao.getCliente(id);
		if (cliente.getId() == null) {
			throw new Exception();
		} else {
			model.addAttribute("cliente", cliente);

			// verifica se é pj ou pf
			if (cliente.getCnpj() == null) {
				return "cliente/mostra_pf";
			} else {
				return "cliente/mostra_pj";
			}
		}

	}

	/* EDITA CADASTRO DE PESSOA FISICA */
	@RequestMapping(value = "AlteraCliente/{id}", method = RequestMethod.POST)
	public String edita(Model model, Cliente c) {
		dao.atualiza(c);
		return "redirect:cliente/lista";
	}

	/* TABELA COM CLIENTES CADASTRADOS */
	@RequestMapping(value = "ListaCliente", method = RequestMethod.GET)
	public String listaClientes(Model model) {
		model.addAttribute("lista", dao.lista());
		return "cliente/lista";
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
