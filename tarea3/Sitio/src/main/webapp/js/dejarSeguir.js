$(document).ready(function(){
	$('#btnNoSeguir2').on('click', function(){
		var usuario = $('#usuario').val();
		$.ajax({
			url: 'dejarSeguirUsuario',
			type: 'post',
			data : {
				'usuario': usuario,
			},
			success: function(response){
				$('span#segs').text(response);
				document.getElementById('btnNoSeguir2').style.display='none';
				document.getElementById('btnSeguir2').style.display='block';
			}
		})
	}
)
	
	$('#btnNoSeguir').on('click', function(){
		var usuario = $('#usuario').val();
		$.ajax({
			url: 'dejarSeguirUsuario',
			type: 'post',
			data : {
				'usuario': usuario,
			},
			success: function(response){
				$('span#segs').text(response);
				document.getElementById('btnNoSeguir').style.display='none';
				document.getElementById('btnSeguir').style.display='block';
			}
		})
	}
)

})