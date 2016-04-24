<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="res" tagdir="/WEB-INF/tags"%>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/navbar.jsp" />
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					Cliente <strong> ${cliente.nome} </strong>
				</h3>
			</div>
			<br /> <br />

			<div class="row">

				<form:form class="form-horizontal" action="cliente/atualiza"
					modelAttribute="cliente" method="post" id="cadastro">
					<fieldset>
					
						<c:if test="${not empty msgSucesso}">
							<div class="alert alert-success col-md-7 col-md-offset-3">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> ${msgSucesso}
							</div>
						</c:if>

						<c:if test="${not empty msgFalha}">
							<div class="alert alert-danger col-md-7 col-md-offset-3">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> ${msgFalha}
							</div>
						</c:if>
					
				<div class="form-group">
					<label class="col-md-3 control-label">Ações: </label>
					<div class="col-md-5">
						<div class="btn-group" role="group" aria-label="...">
							<a href="${requestScope['javax.servlet.forward.request_uri']}#" class="btn btn-default" id="editar">Atualiza cliente</a>
							<a href="coleta/agenda/${cliente.id}" class="btn btn-default">Agenda coleta</a>
							<a href="coleta/agenda/${cliente.id}" class="btn btn-default">Ver coletas</a>
						</div>
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-md-3 control-label"></label>
					<div class="col-md-7">
					<hr>
					</div>
				</div>
				

						<!-- Form Name -->

						<div class="form-group">
							<div class="col-md-5">
								<form:input path="id" name="id" type="hidden"
									class="form-control input-md" required="" />
							</div>
						</div>
						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-3 control-label" for="nome"> <c:if
									test="${not empty cliente.cpf}">Nome*</c:if> <c:if
									test="${not empty cliente.cnpj}">Razão Social*</c:if>
							</label>
							<div class="col-md-3">
								<form:input path="nome" name="nome" type="text"
									placeholder="Digite seu nome completo"
									class="form-control input-md" required="" />

							</div>

							<c:if test="${not empty cliente.cpf}">
								<label class="col-md-1 control-label" for="cpf">CPF*</label>
								<div class="col-md-3">
									<form:input path="cpf" id="cpf" name="cpf" type="text"
										placeholder="Digite um CPF válido"
										class="form-control input-md" required="" maxlength="20" />

									<spring:bind path="cpf">
										<c:if test="${status.error}">
											<p class="text-danger">
												<c:out value="${status.errorMessage}" />
											</p>
										</c:if>
									</spring:bind>

								</div>
							</c:if>
							<c:if test="${not empty cliente.cnpj}">
								<label class="col-md-1 control-label" for="cnpj">CNPJ*</label>
								<div class="col-md-3">
									<form:input path="cnpj" id="cnpj" name="cnpj" type="text"
										placeholder="Digite um CNPJ válido"
										class="form-control input-md" required="" maxlength="20" />

									<spring:bind path="cnpj">
										<c:if test="${status.error}">
											<p class="text-danger">
												<c:out value="${status.errorMessage}" />
											</p>
										</c:if>
									</spring:bind>
								</div>
							</c:if>
						</div>

						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-3 control-label" for="email">Email</label>
							<div class="col-md-3">
								<form:input path="email" name="email" type="text"
									placeholder="Digite um e-mail válido"
									class="form-control input-md" required="" />

							</div>
							<label class="col-md-1 control-label" for="telefone">Telefone*</label>
							<div class="col-md-3">
								<form:input path="telefone" name="telefone" type="text"
									placeholder="Telefone para contato"
									class="form-control input-md" />

							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" for="endereco.cep">CEP</label>
							<div class="col-md-3">
								<form:input path="endereco.cep" name="cep" type="text"
									placeholder="" class="form-control input-md" id="cep" />

							</div>
						</div>


						<div class="form-group">
							<label class="col-md-3 control-label" for="logradouro">Logradouro*</label>
							<div class="col-md-4">
								<form:input path="endereco.logradouro" id="logradouro"
									name="logradouro" type="text"
									placeholder="Digite o nome da rua"
									class="form-control input-md" required="" maxlength="100" />
							</div>

							<label class="col-md-1 control-label" for="numero">Número*</label>
							<div class="col-md-2">
								<form:input path="endereco.numero" name="numero" type="text"
									class="form-control input-md" maxlength="5" />

							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" for="endereco.complemento">Complemento</label>
							<div class="col-md-3">
								<form:input path="endereco.complemento" name="complemento"
									type="text" placeholder="" class="form-control input-md" />

							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" for="endereco.referencia">Ponto
								de Referência:</label>
							<div class="col-md-4">
								<form:input path="endereco.referencia" name="referencia"
									type="text" placeholder="" class="form-control input-md" />

							</div>
							<label class="col-md-1 control-label" for="bairro">Bairro*</label>
							<div class="col-md-2">
								<form:input path="endereco.bairro" id="bairro" name="bairro"
									type="text" placeholder="" class="form-control input-md"
									required="" />

							</div>
						</div>


						<div class="form-group">
							<label class="col-md-3 control-label" for="cidade">Cidade*</label>
							<div class="col-md-4">
								<form:input path="endereco.cidade" id="cidade" name="cidade"
									type="text" placeholder="" class="form-control input-md"
									required="" />

							</div>
							<label class="col-md-1 control-label" for="estado">Estado
								*</label>
							<div class="col-md-2">
								<form:select path="endereco.estado" id="estado" name="estado"
									class="form-control">
									<option value="RJ">RJ</option>
								</form:select>
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-3 control-label" for="ativo">Status
								do Cliente *</label>
							<div class="col-md-3">
								<form:select path="ativo" id="ativo" name="ativo"
									class="form-control">
									<option value="0"
										<c:if test="${cliente.ativo == false}">selected</c:if>>Inativo</option>
									<option value="1"
										<c:if test="${cliente.ativo == true}">selected</c:if>>Ativo</option>
								</form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" for="dataCadastro">Data
								de Cadastro:*</label>
							<div class="col-md-4">
								<p class="control-label" style="text-align: left !important;">
									<fmt:formatDate value="${cliente.dataCadastro}"
										pattern="dd/MM/yyyy HH:mm:ss" />
								</p>
							</div>
						</div>


						<!-- Button -->
						<div class="form-group">
							<label class="col-md-3 control-label" for="submit"></label>
							<div class="col-md-4">
								<input type="submit" name="submit"
									class="btn btn-primary btn-block" value="Atualizar" />
							</div>
						</div>

					</fieldset>

				</form:form>
			</div>



		</div>
	</div>

	<res:valida />

	<script type="text/javascript">
		$("#cadastro input").prop("disabled", true);
		$("#cadastro select").prop("disabled", true);
		$("#editar").prop("disabled", false);
		$(function() {
			$('#editar').click(function() {
				$("#cadastro input").prop("disabled", false);
				$("#cadastro select").prop("disabled", false);
				$('#editar').hide("slow");
			})
		})
	</script>
</body>
</html>
