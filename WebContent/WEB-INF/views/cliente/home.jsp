<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SGLE / Gerenciar</title>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
</head>
<body>
<jsp:include page="/WEB-INF/views/include/navbar.jsp" />

            <div class="container">
                <!-- Page Heading -->
                
                <div class="row">
                <div class="col-lg-12">
				<c:if test="${not empty msgSucesso}">
					<div class="alert alert-success alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						${msgSucesso}
					</div>
				</c:if>
				<c:if test="${not empty msgFalha}">
					<div class="alert alert-danger alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						${msgFalha}
					</div>
				</c:if>
			</div>
                </div>
                
                <div class="row">
                    <div class="col-lg-7">
				    <div id="form" class="content">
    				<h4> Cadastro de Cliente </h4>
    				
    				<!-- Single button -->
    				
    				<c:if test="${param.tipoCliente == null}">
    				<c:set value="active" var="active" scope="request"></c:set>
    				</c:if>
    				
    				
					<ul class="nav nav-pills">
						<li role="presentation" class="<c:out value="${param.tipoCliente=='pf' ? 'active' : ''}" /> <c:out value="${requestScope.active != null ? 'active' : ''}" />"><a href="cliente/cadastro?tipoCliente=pf">Pessoa Física</a></li>
						<li role="presentation" class="<c:out value="${param.tipoCliente=='pj' ? 'active' : ''}"/> "><a href="cliente/cadastro?tipoCliente=pj">Pessoa Jurídica</a></li>
					</ul>
					
					<br /> <br />

					<c:choose>
					<c:when test="${param.tipoCliente == 'pf'}">
					<c:import url="form_cadastro_pf.jsp"></c:import>
					</c:when>
					<c:when test="${param.tipoCliente == 'pj'}">
					<c:import url="form_cadastro_pj.jsp"></c:import>
					</c:when>
					<c:otherwise>
					<c:import url="form_cadastro_pf.jsp"></c:import>
					</c:otherwise>
					
					</c:choose>
					

				</div> 
			</div>
			<!--  div lado esquerdo (cadastro) -->
			
			<div class="col-lg-5">
			<br /> <br /> <br /> <br /> <br /> <br />
			<c:import url="form_busca_cliente.jsp"></c:import>
			</div>
			
			
			</div>
			</div>



</body>
</html>