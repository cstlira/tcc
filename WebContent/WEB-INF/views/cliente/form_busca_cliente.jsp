<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Busca de Clientes</h3>
			</div>
			<br /> <br />
			
			<form:form class="form-horizontal"
				action="cliente/busca"
				method="post" id="cadastro" modelAttribute="cliente">
				<fieldset>
					<!-- Form Name -->

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="nome">Nome*</label>
						<div class="col-md-7">
							<form:input path="nome" name="nome" type="text"
								placeholder="Digite o nome do cliente"
								class="form-control input-md" required=""  id="nome"/>

						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="nome">CPF/CNPJ*</label>
						<div class="col-md-7">
							<form:input path="registro" name="registro" type="text"
								placeholder="Digite o CPF ou CNPJ do cliente"
								class="form-control input-md" required="" id="registro" />

						</div>
					</div>
					

					<div class="form-group">
						<label class="col-md-4 control-label" for="submit"></label>
						<div class="col-md-4">
							<input name="submit" type="submit" class="btn btn-default"
								value="Buscar" />
						</div>
					</div>

					<div class="form-group">
					<label class="col-md-4 control-label" for="error"></label>
					
					<div class="col-md-7">
						<spring:bind path="nome">
						  <c:if test="${status.error}">
						    <div class="alert alert-danger fade in">
    							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    							<c:out value="${status.errorMessage}"> </c:out>
  							</div>
						  </c:if>
						</spring:bind>
					</div>
					</div>




			</fieldset>
			</form:form>
			
					<div class="form-group">
					<div class="col-md-offset-4">
										<a href="cliente/busca?all">Listar todos clientes</a>
					</div>
					</div>
			
</div>
</body>
</html>
