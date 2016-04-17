package br.estacio.tcc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	  @Override
	  public boolean preHandle(HttpServletRequest request, 
	      HttpServletResponse response,
	      Object controller) throws Exception {

	      String uri = request.getRequestURI();
	      if(uri.endsWith("Login") ||
	         uri.contains("resources")){
	        return true;
	      }
	      
	      if(request.getSession().getAttribute("usuarioLogado")!=null){
	    	  return true;
	      }

	      response.sendRedirect("Login");
	      return false;
	  }
	}