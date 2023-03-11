$(document).ready(function(){
	$('#btnSeguir').on('click', function(){
		var usuario = $('#usuario').val();
		$.ajax({
			url: 'seguirUsuario',
			type: 'post',
			data : {
				'usuario': usuario,
			},
			success: function(response){
				$('span#segs').text(response);
				document.getElementById('btnSeguir').style.display='none';
				document.getElementById('btnNoSeguir').style.display='block';
			}
		})
	}
)

	$('#btnSeguir2').on('click', function(){
		var usuario = $('#usuario').val();
		$.ajax({
			url: 'seguirUsuario',
			type: 'post',
			data : {
				'usuario': usuario,
			},
			success: function(response){
				$('span#segs').text(response);
				document.getElementById('btnSeguir2').style.display='none';
				document.getElementById('btnNoSeguir2').style.display='block';
			}
		})
	}
)

})
