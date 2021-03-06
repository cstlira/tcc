package br.estacio.tcc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.estacio.tcc.dao.JdbcUsuarioDao;
import br.estacio.tcc.modelo.Usuario;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	JdbcUsuarioDao dao;
	

	@RequestMapping(value="Login", method=RequestMethod.GET)
	public String showLoginForm() {
		return "login";
	}
	
	@RequestMapping(value="Login", method=RequestMethod.POST)
	public String verifyLogin(@RequestParam String login, @RequestParam String password,HttpServletRequest req, HttpSession session, RedirectAttributes redir, Model model) {
		
		Usuario usuario = dao.loginUser(login, password);
		
		if (usuario == null) {
			logger.error("Algu�m tentou logar-se no sistema com as credenciais "+login+"/"+password+" usando o IP: "+req.getRemoteAddr());
			redir.addFlashAttribute("loginError","Erro. Usu�rio ou senha inv�lidos. </ br>" + req.getRemoteAddr());
			return "redirect:Login";
		}
		
		
			logger.info("O usuario "+ usuario.getNome()+" logou-se no sistema com o IP: "+req.getRemoteAddr());
			session.setAttribute("usuarioLogado", usuario);
			
			String url = null;
			if(session.getAttribute("url")!=null) {
			url = (String) session.getAttribute("url");
			}
			
			
			if(url!=null && !url.contains("Login")) {
			return "redirect:"+url;
			}
			
			return "redirect:Home";
			

	}
	
	
	@RequestMapping("Logout")
	public String efetuaLogout(HttpSession session) {
		session.invalidate();
		return "redirect:Login";
	}
	
	/* Pagina Principal */
	@RequestMapping(value = "Home")
	public String listaMenu() {
		return "home";
	}
	
	
}
