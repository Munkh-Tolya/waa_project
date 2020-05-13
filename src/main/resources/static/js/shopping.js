"use strict";
$(function(){
	$('.addToCard').on('click',addProduct);
});

function addProduct(event){
	event.preventDefault();
	const productId = $(this).attr("value");
	$.ajax("cart/",{
		method:"POST",
		data: {"cmd":"addProductToCard",
				"productId":productId,
				"quantity":1},
		dataType: "json"
	})
	.done(function(response){
		$('#itemCount').text(response.cardSize);
		showSuccess(response.message);
	})
	.fail(function(xhr, textStatus, errorThrown){
		let errorObj = JSON.parse(xhr.responseText);
		showError(errorObj.message);
	})
}


