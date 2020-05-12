"use strict";
$(function(){
	console.log('gg');
	$('.addToCard').on('click',addProduct);
});

function addProduct(event){
	event.preventDefault();
	const productId = $(this).attr("value");
	$.ajax("http://localhost:8080/card/add",{
		method:"POST",
		data: {"productId":productId,
			   "quantity":1},
		dataType: "json"
	})
	.success(function(response){
		if(response.success){
			$('#itemCount').text(response.cardSize);
			showSuccess(response.message);
		}else{
			showError(response.message);
		}
	})
	.error(function(){
		alert("Failed to add this product on shopping card!");
	})
}


