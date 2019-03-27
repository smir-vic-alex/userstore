$(document).ready(function(){




	/*  Hamburger Menu & Icon  */
	$('.hamburger').on('click', function(e){
		
		e.preventDefault();
		$(this).toggleClass('opned');
		$('header nav').toggleClass('active');
		
	});




	/*  Advanced search form & Icon  */
	$('#advanced_search_btn').on("click", function(e){
		e.preventDefault();

		var ads_box =$('.advanced_search');
		
		if(!ads_box.hasClass('advanced_displayed')){

			$(this).addClass('active');
			ads_box.stop().fadeIn(200).addClass('advanced_displayed');

		}else{

			$(this).removeClass('active');
			ads_box.stop().fadeOut(200).removeClass('advanced_displayed');

		}

	});

		function login (){
			var login = $('#login').val();
			var password = $('#password').val();
			var arr = { "login":login,
				"password":password
			};
			$.ajax({
				url: '/rest/auth/login',
				type: 'POST',
				data: JSON.stringify(arr),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
				success: function(jsondata) {
					window.location.href = jsondata.redirectUrl;
				}
			});
		}


});