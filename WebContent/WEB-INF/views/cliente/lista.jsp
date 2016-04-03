
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Tarefas</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<link type="text/css" href="resources/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
<br />
<div class="container">
	<a href="novaTarefa">Criar nova tarefa</a>

	<br />
	<br />
	
	<h3> Lista de Clientes Pessoa Fisica </h3>
	
	<table class="table table-striped">
		<tr>
			<th>ID </th>
			<th>Nome</th>
			<th>E-mail</th>
			<th>Telefone</th>
			<th>CPF</th>
			<th>Endereço</th>
		</tr>
		<c:forEach items="${lista}" var="cliente">
		<c:if test="${not empty cliente.cpf}">
			<tr>
				<td>${cliente.id}</td>
				<td>${cliente.nome}</td>
				<td>${cliente.email}</td>
				<td>${cliente.telefone}</td>
				<td>${cliente.cpf}</td>
				<td>${cliente.endereco}</td>						
			</tr>
		</c:if>
		</c:forEach>
	</table>
	
	
	
	
<a href="../listaMenu"> Voltar para o menu</a>
</div>
</body>
</html>