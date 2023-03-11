$(document).ready(function(){
	$('#nickname').on('keyup', function(){
		var nickname = $('#nickname').val();
		console.log(nickname)
		if (nickname == null){
			$('#nickname').siblings("span").innerHTML(" ");
		}
		$.ajax({
			url: 'altaUsuarioDinamico',
			type: 'post',
			data : {
				'nickname': nickname,
			},
			success: function(response){
				$('#nickname').siblings("span").text(response);
			}
		})
	}
)})
