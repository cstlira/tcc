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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.estacio.tcc.dao.JdbcClienteDao;
import br.estacio.tcc.modelo.Cliente;
import br.estacio.tcc.validator.BuscaClienteValidator;

@Controller
@RequestMapping("cliente")
public class ClienteController {

	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	JdbcClienteDao dao;

	@Autowired
	BuscaClienteValidator buscaClienteValidator;

	@RequestMapping(value = "cadastro", method = RequestMethod.GET)
	public String formCadastro(Cliente cliente) {
		return "cliente/home";
	}

	/* CADASTRO DE CLIENTES */
	@RequestMapping(value = "cadastro", method = RequestMethod.POST)
	public String formCadastroSubmit(Model model, Cliente c, RedirectAttributes redir) {
		// Pega CPF ou CNPJ do formulário
		String registro = (c.getCpf() == null ? "" : c.getCpf()) + (c.getCnpj() == null ? "" : c.getCnpj());
		// Se houver algum cliente com o CPF ou CNPJ retorna erro e redireciona
		// pro cliente
		if (dao.buscaClientePorCPFouCNPJ(registro) != null) {
			redir.addFlashAttribute("msgFalha", "Erro ao adicionar cliente. Cadastro já existe.");
			return "redirect:" + dao.buscaClientePorCPFouCNPJ(registro).getId();
		}
		dao.adiciona(c);
		redir.addFlashAttribute("msgSucesso", "Cliente adicionado com sucesso.");
		return "redirect:" + dao.buscaClientePorCPFouCNPJ(registro).getId();
	}

	/* BUSCA DE CLIENTES */

	@RequestMapping(value = "busca", method = RequestMethod.POST)
	public String buscaCliente(Model model, @Valid Cliente c, BindingResult bindingResult) {
		String nome = c.getNome();

		c.reset();
		if (bindingResult.hasErrors()) {
			return "cliente/home";
		}
		List<Cliente> clientes = dao.buscaClientesPorNome(nome);

		if (clientes.size() == 1) {
			return "redirect:" + clientes.get(0).getId();
		}

		if (clientes.isEmpty()) {
			bindingResult.rejectValue("nome", "nome.notfound", "Cliente(s) não encontrado(s).");
			return "cliente/home";
		}
		model.addAttribute("clientesEncontrados", clientes);
		return "cliente/lista_clientes_encontrados";
	}
	
	@RequestMapping(value = "busca", method = RequestMethod.GET, params="all")
	public String buscaTodosClientes(Model model) {
		model.addAttribute("clientesEncontrados", dao.buscaTodosClientes());
		return "cliente/lista_clientes_encontrados";
	}
	

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String mostrarCliente(Model model, Cliente c, @PathVariable String id) {
		Cliente cliente = dao.buscaClientePorId(id);
		model.addAttribute("cliente", cliente);
		return "cliente/mostra_cliente";
	}

	@RequestMapping(value = "atualiza", method = RequestMethod.POST)
	public String atualizaCliente(Model model, Cliente c, RedirectAttributes redirectAttributes) {
		dao.atualiza(c);
		redirectAttributes.addFlashAttribute("msgSucesso", "Cliente atualizado com sucesso!");
		return "redirect:" + c.getId();
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
