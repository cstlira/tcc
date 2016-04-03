<%@ tag language="java" pageEncoding="ISO-8859-1"%>


<!--  JAVASCRIPT PARA VALIDAÇÃO DO FORM DE CADASTRO PF -->

<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.mask.js"></script>

	<script type="text/javascript">
	
		$( document ).ready( function () {
			
			/* Mascaras p/ formatação dos campos */
			$('#cep').mask('00000-000');
			$('#cnpj').mask('00.000.000/0000-00', {reverse: true});
			$('#telefone').mask('(00) 0000-00000');

			
			
			
			
			$.validator.addMethod("regex", function(value, element, regexp) {
				        var check = false;
				        return this.optional(element) || regexp.test(value);
				    },
				    "Entrada inválida."
				);
			
			$.validator.addMethod("cnpj", function(cnpj, element) {
				   cnpj = jQuery.trim(cnpj);
					
					// DEIXA APENAS OS NÚMEROS
				   cnpj = cnpj.replace('/','');
				   cnpj = cnpj.replace('.','');
				   cnpj = cnpj.replace('.','');
				   cnpj = cnpj.replace('-','');

				   var numeros, digitos, soma, i, resultado, pos, tamanho, digitos_iguais;
				   digitos_iguais = 1;

				   if (cnpj.length < 14 && cnpj.length < 15){
				      return this.optional(element) || false;
				   }
				   for (i = 0; i < cnpj.length - 1; i++){
				      if (cnpj.charAt(i) != cnpj.charAt(i + 1)){
				         digitos_iguais = 0;
				         break;
				      }
				   }

				   if (!digitos_iguais){
				      tamanho = cnpj.length - 2
				      numeros = cnpj.substring(0,tamanho);
				      digitos = cnpj.substring(tamanho);
				      soma = 0;
				      pos = tamanho - 7;

				      for (i = tamanho; i >= 1; i--){
				         soma += numeros.charAt(tamanho - i) * pos--;
				         if (pos < 2){
				            pos = 9;
				         }
				      }
				      resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
				      if (resultado != digitos.charAt(0)){
				         return this.optional(element) || false;
				      }
				      tamanho = tamanho + 1;
				      numeros = cnpj.substring(0,tamanho);
				      soma = 0;
				      pos = tamanho - 7;
				      for (i = tamanho; i >= 1; i--){
				         soma += numeros.charAt(tamanho - i) * pos--;
				         if (pos < 2){
				            pos = 9;
				         }
				      }
				      resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
				      if (resultado != digitos.charAt(1)){
				         return this.optional(element) || false;
				      }
				      return this.optional(element) || true;
				   }else{
				      return this.optional(element) || false;
				   }
				}, "Informe um CNPJ válido."); // Mensagem padrão
		
			
		
			$( "#cadastro" ).validate( {
				rules: {
					nome: {required: true, regex: /^[a-zA-Z\s\u00C0-\u00FF]*$/},
					email: {regex: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/ },
					telefone: {required: true, regex:/^[()\d-\s]+$/, minlength:14},
					cnpj: {required: true, regex:  /^([0-9]{3}\.?[0-9]{3}\.?[0-9]{3}\-?[0-9]{2}|[0-9]{2}\.?[0-9]{3}\.?[0-9]{3}\/?[0-9]{4}\-?[0-9]{2})$/
					, minlength:11, cnpj: true},
					'endereco.cep': {required: true, regex: /^\d{5}-\d{3}$/},
					'endereco.logradouro': {required: true, regex: /^[a-zA-Z\s\u00C0-\u00FF]*$/},
					'endereco.numero': {required: true, regex: /^[0-9]*$/},
					'endereco.bairro': {required: true, regex: /^[a-zA-Z\s\u00C0-\u00FF]*$/},
					'endereco.cidade': {required: true, regex: /^[a-zA-Z\s\u00C0-\u00FF]*$/}
					
				},
				messages: {
					nome: {required: "Digite a razão social.", regex: "Digite somente letras."},
					email: {regex: "Por favor, digite um email válido."},
					telefone: "Por favor, digite um telefone válido.",
					cnpj: {required: "Informe o CNPJ.", regex: "Informe o CNPJ.", cnpj: "CNPJ não é válido."},
					'endereco.cep': {required: "CEP requerido", regex: "CEP não é válido."},
					'endereco.logradouro': {required: "Digite o logradouro."},
					'endereco.numero': {required: "Digite o número.", regex: "Digite um número válido."},
					'endereco.bairro': {required: "Digite o bairro."},
					'endereco.cidade': {required: "Digite a cidade."},
					
				},
				errorElement: "em",
				errorPlacement: function ( error, element ) {
					// Add the `help-block` class to the error element
					error.addClass( "help-block" );

					// Add `has-feedback` class to the parent div.form-group
					// in order to add icons to inputs
					element.parents( ".col-md-5" ).addClass( "has-feedback" );

					if ( element.prop( "type" ) === "checkbox" ) {
						error.insertAfter( element.parent( "label" ) );
					} else {
						error.insertAfter( element );
					}

					// Add the span element, if doesn't exists, and apply the icon classes to it.
					if ( !element.next( "span" )[ 0 ] ) {
						$( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element );
					}
				},
				success: function ( label, element ) {
					// Add the span element, if doesn't exists, and apply the icon classes to it.
					if ( !$( element ).next( "span" )[ 0 ] ) {
						$( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) );
					}
				},
				highlight: function ( element, errorClass, validClass ) {
					$( element ).parents( ".col-md-5" ).addClass( "has-error" ).removeClass( "has-success" );
					$( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" );
				},
				unhighlight: function ( element, errorClass, validClass ) {
					$( element ).parents( ".col-md-5" ).addClass( "has-success" ).removeClass( "has-error" );
					$( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" );
				} /*,
				
				submitHandler: function(e) {
				    var url = "CadastroCliente";
				    $.ajax({
				           type: "POST",
				           url: url,
				           data: $("#cadastro").serialize(),
				           success: function(data)
				           {
				               $("#content").html(data);
				           }
				         });
				    return false;
				} */
				
				
			} );
		} );
</script>



