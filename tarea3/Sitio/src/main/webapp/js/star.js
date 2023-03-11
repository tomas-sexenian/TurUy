var star = $(".star");


star.click(function() {
  if (star.hasClass("starred")) {
    $.ajax({
		url: 'desmarcarFavorita',
		type: 'post',
		success: function(response){
    		let str = document.getElementById("favs_act").innerHTML; 
			let cant_favoritos = Number(document.getElementById("favs_act").innerHTML.match(/[0-9]+(\s)/)[0]);
			let res = str.replace(/[0-9]+(\s)/, String(cant_favoritos - 1) + " ");
			document.getElementById("favs_act").innerHTML = res;
			star.removeClass("starred");
    		star.addClass("unstarred");
		}
		})
  } else if (star.hasClass("unstarred")) {
    $.ajax({
		url: 'marcarFavorita',
		type: 'post',
		success: function(response){
    		let str = document.getElementById("favs_act").innerHTML; 
			let cant_favoritos = Number(document.getElementById("favs_act").innerHTML.match(/[0-9]+(\s)/)[0]);
			let res = str.replace(/[0-9]+(\s)/, String(cant_favoritos + 1) + " ");
			document.getElementById("favs_act").innerHTML = res;
			star.removeClass("unstarred");
    		star.addClass("starred");
		}
		})
  } else {
        $.ajax({
		url: 'marcarFavorita',
		type: 'post',
		success: function(response){
    		let str = document.getElementById("favs_act").innerHTML; 
			let cant_favoritos = Number(document.getElementById("favs_act").innerHTML.match(/[0-9]+(\s)/)[0]);
			let res = str.replace(/[0-9]+(\s)/, String(cant_favoritos + 1) + " ");
			document.getElementById("favs_act").innerHTML = res;
			star.addClass("starred");
		}
		})
  }
});