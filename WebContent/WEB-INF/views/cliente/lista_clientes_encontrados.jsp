<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="res" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostra cliente</title>
<res:resources />
</head>
<body>
<res:navbar />
<div class="container">
<div class="row">
<div class="col-md-10 col-md-offset-1"> 
<h4 class="text-uppercase">Clientes encontrados:</h4>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Nome/Razão Social</th>
        <th>CPF/CNPJ</th>
        <th>Endereço</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="cliente" items="${clientesEncontrados}">
        <tr>
        <td><a href="MostraCliente?id=${cliente.id}"><c:out value="${cliente.nome}" /></a></td>
        <td><c:out value="${cliente.cpf}" /><c:out value="${cliente.cnpj}" /></td>
        <td><c:out value="${cliente.endereco.logradouro}, ${cliente.endereco.numero} - ${cliente.endereco.bairro} - ${cliente.endereco.cidade} - ${cliente.endereco.estado}" /></td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</div>
</div>
</body>
</html>