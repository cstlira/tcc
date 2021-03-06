<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Cadastro de Pessoa Jur�dica</h3>
			</div>
			<br /> <br />
			
			<form:form class="form-horizontal"
				action="cliente/cadastro" modelAttribute="cliente"
				method="post" id="cadastro">
				<fieldset>
					<!-- Form Name -->

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="nome">Raz�o Social*</label>
						<div class="col-md-5">
							<form:input path="nome" name="nome" type="text"
								placeholder="Digite a raz�o social da empresa"
								class="form-control input-md" required="" />

						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="email">Email</label>
						<div class="col-md-5">
							<form:input path="email" name="email" type="text"
								placeholder="Digite um e-mail v�lido"
								class="form-control input-md" required=""/>

						</div>
					</div>


					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="telefone">Telefone*</label>
						<div class="col-md-5">
							<form:input path="telefone" name="telefone" type="text"
								placeholder="Telefone para contato"
								class="form-control input-md" />

						</div>
						
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="cnpj">CNPJ*</label>
						<div class="col-md-5">
							<form:input path="cnpj" id="cnpj" name="cnpj" type="text"
								placeholder="Digite um CNPJ v�lido" class="form-control input-md"
								required="" maxlength="18"/>
							<form:errors path="cnpj"></form:errors>

						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="cep">CEP*</label>
						<div class="col-md-5">
							<form:input path="endereco.cep" id="cep" name="cep" type="text"
								placeholder="Digite um CEP v�lido."
								class="form-control input-md" required=""  maxlength="9"/>

						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="logradouro">Logradouro*</label>
						<div class="col-md-5">
							<form:input path="endereco.logradouro" id="logradouro"
								name="logradouro" type="text" placeholder="Digite o nome da rua"
								class="form-control input-md" required="" />

						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="numero">N�mero*</label>
						<div class="col-md-5">
							<form:input path="endereco.numero" name="numero" type="text"
								class="form-control input-md" />

						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="endereco.complemento">Complemento</label>
						<div class="col-md-5">
							<form:input path="endereco.complemento" name="complemento"
								type="text" placeholder="" class="form-control input-md" />

						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="endereco.referencia">Ponto de Refer�ncia:</label>
						<div class="col-md-5">
							<form:input path="endereco.referencia" name="referencia"
								type="text" placeholder="" class="form-control input-md" />

						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="bairro">Bairro*</label>
						<div class="col-md-5">
							<form:input path="endereco.bairro" id="bairro" name="bairro"
								type="text" placeholder=""
								class="form-control input-md" required="" />

						</div>
					</div>


					<div class="form-group">
						<label class="col-md-4 control-label" for="cidade">Cidade*</label>
						<div class="col-md-5">
							<form:input path="endereco.cidade" id="cidade" name="cidade"
								type="text" placeholder="" class="form-control input-md"
								required="" />

						</div>
					</div>


					<!-- Select Basic -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="estado">Estado *</label>
						<div class="col-md-4">
							<form:select path="endereco.estado" id="estado" name="estado" class="form-control">
								<option value="RJ">RJ</option>
							</form:select>
						</div>
					</div>
					<!-- Button -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="submit"></label>
						<div class="col-md-4">
							<input type="submit" name="submit" class="btn btn-default"
								value="Cadastrar" />
						</div>
					</div>

				</fieldset>
			</form:form>


</div>
<script type="text/javascript" src="resources/js/jquery.validate.min.js" charset="utf-8"></script>
<script type="text/javascript" src="resources/js/jquery.mask.js" charset="utf-8"></script>
<script type="text/javascript" src="resources/js/validacao_pj.js" charset="utf-8"></script>
</body>
</html>
