<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
</head>
<body>
<jsp:include page="/WEB-INF/views/include/navbar.jsp" />
<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"> Agendamento de Coleta - <strong> ${cliente.nome} </strong> </h3>
			</div>
 <br />
 <div class="row">
 <div class="col-md-8">
			<!--  Produtos -->
				<form:form method="post" name="addItensForm" id="addItensForm" modelAttribute="agendamento" action="coleta/agenda/addItemColeta" class="form-horizontal">
					<fieldset>	
					<form:input path="cliente.id" name="idCliente" type="hidden"
								class="form-control input-md" required="" value="${cliente.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label" for="produto">Produto*</label>
						<div class="col-md-3">
							<form:select class="form-control" path="itemColeta.idProduto">
    							<form:options items="${listaProdutos}" />
							</form:select>
						</div>
						<label class="col-md-1 control-label" for="produto">Qtd*</label>
						<div class="col-md-2">
							<form:input path="itemColeta.quantidade" name="qtd" type="number"
								placeholder="Qtd."
								class="form-control input-md" required="" min="0"/>
						</div>

						<div class="form-group">
							<div class="col-md-2">
								<input type="submit" name="submit"
									class="btn btn-primary btn-block" value="Adicionar" />
							</div>
						</div>
						
					</div>	
					</fieldset>
					</form:form>
					
			<form:form method="post" name="agendamentoForm" id="agendamentoForm" modelAttribute="agendamento" action="coleta/agenda" class="form-horizontal">
				<fieldset>
					<form:input path="cliente.id" name="idCliente" type="hidden"
								class="form-control input-md" required="" value="${cliente.id}" />
				
				<!--  Data de Coleta -->
					<div class="form-group">
						<label class="col-md-2 control-label" for="nome">Data*</label>
						<div class="col-md-3">
							<form:input path="data" name="data" type="text"
								placeholder="Digite a data de coleta"
								class="form-control input-md" required="" value="" />
						</div>
					</div>
					
						
					<div class="form-group">
							<label class="col-md-2 control-label" for="submit"></label>
							<div class="col-md-4">
								<input type="submit" name="submit"
									class="btn btn-primary btn-block" value="Agendar" />
							</div>
					</div>
						
				</fieldset>
		</form:form>	
	</div>


<div class="col-md-4">
 <table class="table table-hover">
    <thead>
      <tr>
        <th>Item</th>
        <th>Quantidade</th>
        <th>Peso total</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="item" items="${itensAdicionados}">
      <c:if test="${item.idCliente == cliente.id}">
        <tr>
        <td><c:out value="${item.descricao}" /></td>
        <td><c:out value="${item.quantidade}" /></td>
        <td><c:out value="${item.peso}" /></td>
      </tr>
      </c:if>
      </c:forEach>
    </tbody>
  </table>
</div>
	
	
</div>
			
</div>
</div>
</body>
</html>