<%@ tag language="java" pageEncoding="ISO-8859-1"%>


<!--  JAVASCRIPT PARA VALIDAÇÃO DO FORM DE CADASTRO PF -->

<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.mask.js"></script>

	<script type="text/javascript">
	
		$( document ).ready( function () {
			
			/* Mascaras p/ formatação dos campos */
			$('#cep').mask('00000-000');
			$('#cpf').mask('000.000.000-00', {reverse: true});
			$('#telefone').mask('(00) 0000-00000');

			
			
			
			
			$.validator.addMethod("regex", function(value, element, regexp) {
				        var check = false;
				        return this.optional(element) || regexp.test(value);
				    },
				    "Entrada inválida."
				);
			
			$.validator.addMethod("cpf", function(value, element) {
				
				   	value = jQuery.trim(value);

				    value = value.replace('.','');
				    value = value.replace('.','');
				    cpf = value.replace('-','');
				    while(cpf.length < 11) cpf = "0"+ cpf;
				    var expReg = /^0+$|^1+$|^2+$|^3+$|^4+$|^5+$|^6+$|^7+$|^8+$|^9+$/;
				    var a = [];
				    var b = new Number;
				    var c = 11;
				    for (i=0; i<11; i++){
				        a[i] = cpf.charAt(i);
				        if (i < 9) b += (a[i] * --c);
				    }
				    if ((x = b % 11) < 2) { a[9] = 0 } else { a[9] = 11-x }
				    b = 0;
				    c = 11;
				    for (y=0; y<10; y++) b += (a[y] * c--);
				    if ((x = b % 11) < 2) { a[10] = 0; } else { a[10] = 11-x; }

				    var retorno = true;
				    if ((cpf.charAt(9) != a[9]) || (cpf.charAt(10) != a[10]) || cpf.match(expReg)) retorno = false;

				    return this.optional(element) || retorno;

				}, "Informe um CPF válido");
			
			
			
			
		
			$("#cadastro").validate({
				rules: {
					nome: {required: true, minlength:5, regex: /^[a-zA-Z\s\u00C0-\u00FF]*$/},
					email: {regex: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/ },
					telefone: {required: true, regex:/^[()\d-\s]+$/, minlength:14},
					cpf: {required: true, regex:  /^([0-9]{3}\.){2}([0-9]{3}-)([0-9]{2})$/, minlength:11, cpf: true},
					'endereco.cep': {required: true, regex: /^\d{5}-\d{3}$/},
					'endereco.logradouro': {required: true, regex: /^[a-zA-Z\s\u00C0-\u00FF]*$/},
					'endereco.numero': {required: true, regex: /^[0-9]*$/},
					'endereco.complemento': {regex: /^[a-zA-Z0-9\s\u00C0-\u00FF]*$/},
					'endereco.referencia': {regex: /^[a-zA-Z0-9\s\u00C0-\u00FF]*$/},
					'endereco.bairro': {required: true, regex: /^[a-zA-Z\s\u00C0-\u00FF]*$/},
					'endereco.cidade': {required: true, regex: /^[a-zA-Z\s\u00C0-\u00FF]*$/}
					
				},
				
					messages: {
					nome: {required: "Por favor, digite seu nome.", regex: "Digite somente caracteres."},
					email: {regex: "E-mail inválido."},
					telefone: "Por favor, digite um telefone válido.",
					'endereco.cep': {required: "CEP requerido", regex: "CEP inválido."},
					'endereco.logradouro': {required: "Digite o logradouro."},
					'endereco.numero': {required: "Digite o número.", regex: "Digite um número válido."},
					'endereco.complemento': {regex: "Retire os caracteres especiais."},
					'endereco.referencia': {regex: "Digite os caracteres especiais."},
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
				},
				
				/* submitHandler: function(e) {
				    var url = "CadastroCliente";
				    $.ajax({
				           type: "POST",
				           url: url,
				           data: $("#cadastro").serialize(),
				           success: function(data)
				           {
				               $(body).html(data);
				           }
				         });
				    return false;
				} */
			})
		});
</script>




