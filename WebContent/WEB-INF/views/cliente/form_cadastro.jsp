<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Cadastro SGL</title>
<%@ taglib prefix="res" tagdir="/WEB-INF/tags" %>
<res:resources />
</head>
								
					<div class="col-lg-12">
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i> Cliente / Cadastro
                            </li>
                        </ol>
                    </div>
				<div id="form" class="col-sm-8 col-sm-offset-2">
					<div class="row">
					

					
					
					<div class="form-group">
						<label for="tipocliente">Tipo de Cliente:</label> <select
							class="form-control" id="tipocliente">
							<option value="pf"
								<c:out value="${tipoCliente == 'pf' ? 'selected' : ''}"/>>
								Pessoa Fisica</option>
							<option value="pj"
								<c:out value="${tipoCliente == 'pj' ? 'selected' : ''}"/>>Pessoa
								Juridica</option>
						</select>
					</div>

					<c:choose>
						<c:when test="${tipoCliente == 'pf'}">
							<c:import url="form_cadastro_pf.jsp"></c:import>
						</c:when>
						<c:when test="${tipoCliente == 'pj'}">
							<c:import url="form_cadastro_pj.jsp"></c:import>
						</c:when>
						<c:otherwise>
							<c:import url="form_cadastro_pf.jsp"></c:import>
						</c:otherwise>
					</c:choose>
				</div>
			</div>



	<script type="text/javascript">

	
		$('#tipocliente').on('change', function() {
			var tipoCliente = $("#tipocliente").val();
			$.get("CadastroCliente", {
				tipoCliente : tipoCliente
			}, function(data) {
				$('#content').html(data);
			});
		});

		$("#cep").focusout(function() {
			$.ajax({
				url : "http://api.postmon.com.br/v1/cep/" + $("#cep").val()
			}).then(function(e) {
				$('#logradouro').val(e.logradouro);
				$('#bairro').val(e.bairro);
				$('#cidade').val(e.cidade);
				$('#estado').val(e.estado);
			});
		});
	</script>


</body>
</html>