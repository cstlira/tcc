<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterando cliente</title>
</head>
<body>
  <form:form action="/cliente/edita/${id}" method="post" modelAttribute="cliente">
      <table>
          <tr>
              <form:input path="id" class="form-control input-lg"  type="hidden" />
              
              <td>Nome:</td>
              <td><form:input path="nome" class="form-control input-lg" /></td>
          </tr>
          
           <tr>
              <td>Email:</td>
              <td><form:input path="email" class="form-control input-lg" /></td>
          </tr>
          
          <tr>
              <td>Telefone:</td>
              <td><form:input path="telefone" maxlength="255" class="form-control input-lg"/></td>
          </tr>
          
           <tr>
              <td>CPF:</td>
              <td><form:input path="cpf" maxlength="13" class="form-control input-lg"/></td>
          </tr>
          
           <tr>
              <td>CEP:</td>
              <td><form:input id="cep" path="endereco.cep" maxlength="13" class="form-control input-lg"/></td>
          </tr>
          
          
          <tr>
          
              <td>Logradouro:</td>
              <td><form:input id="logradouro" path="endereco.logradouro" maxlength="255" class="form-control input-lg"/></td>

              <td>Numero:</td>
              <td><form:input path="endereco.numero" maxlength="5" class="form-control input-lg has-success has-feedback"/></td>
          </tr>
          
          <tr>
              <td>Complemento:</td>
              <td><form:input path="endereco.complemento" maxlength="255" class="form-control input-lg"/></td>
          </tr>
          
          <tr>
              <td>Ponto de Referencia:</td>
              <td><form:input  path="endereco.referencia" maxlength="255" class="form-control input-lg"/></td>
          </tr>
          
           <tr>
              <td>Bairro:</td>
              <td><form:input id="bairro" path="endereco.bairro" maxlength="255" class="form-control input-lg"/></td>
          </tr>
          
          
          <tr>
              <td>Cidade:</td>
              <td><form:input id="cidade" path="endereco.cidade" maxlength="255" class="form-control input-lg"/></td>
          </tr>
          
         	<tr>
              <td>Estado:</td>
              <td><form:input id="estado" path="endereco.estado" maxlength="255" class="form-control input-lg"/></td>
          </tr>
          <tr>
              <td> <input type="submit" value="Alterar"/> </td>
          </tr>
      </table>

  </form:form>



</body>
</html>