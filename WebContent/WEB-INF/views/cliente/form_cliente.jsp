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
<body>
		<div id="form" class="col-md-8 col-md-offset-1">
					<div class="row">


			<ol class="breadcrumb">
				<li class="active"><i class="fa fa-dashboard"></i> Cliente /
					Cadastro</li>
			</ol>



		<ul class="nav nav-pills">
    				<li class="active">
    				<a data-toggle="pill" href="#cadastro-cliente-wrapper">Cadastro</a>
    				</li>
    				<li>
    				<a data-toggle="pill" href="#busca-cliente-wrapper">Busca</a>
    				</li>
  		</ul>
					
					
					<br/> <br/>
					<div class="tab-content">
					
					<div id="cadastro-cliente-wrapper" class="tab-pane fade in active">
					<div class="form-group">
						<label for="tipocliente">Tipo de Cliente:</label> <select
							class="form-control" id="tipocliente">
							<option value="pf"
								<c:out value="${tipoCliente == 'pf' ? 'selected' : ''}"/> >
								Pessoa Fisica</option>
							<option value="pj"
								<c:out value="${tipoCliente == 'pj' ? 'selected' : ''}"/> >Pessoa
								Juridica</option>
						</select>
					</div>
					<div id="form-wrapper">
					<c:import url="form_cadastro_pf.jsp"></c:import>
					</div>
					
					</div>
					<div id="busca-cliente-wrapper" class="tab-pane fade">
					<c:import url="mostra_pf.jsp" />
					</div>
					
			</div>
			</div> 
			</div>
			</div>



	<script type="text/javascript">
		$('#tipocliente').on('change', function() {
			var tipoCliente = $("#tipocliente").val();
			$.get("formCadastroCliente?TipoCliente="+tipoCliente, {
				tipoCliente : tipoCliente
			}, function(data) {
				$('#form-wrapper').html(data);
			});
		});
	</script>


</body>
</html>