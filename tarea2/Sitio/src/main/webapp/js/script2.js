$(document).ready(function(){
	$('#correo').on('keyup', function(){
		var correo = $('#correo').val();
		if (correo == null){
			$('#correo').siblings("span").text(" ");
		}
		$.ajax({
			url: 'altaUsuarioDinamico',
			type: 'post',
			data : {
				'correo': correo,
			},
			success: function(response){
				$('#correo').siblings("span").text(response);
			}
		})
	}
)})
