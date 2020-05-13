"use strict";
$(function(){
	$('.addToCard').on('click',addProduct);
	$('#addToCard').on('click',addMultipleProduct);
});

function addProduct(event){
	event.preventDefault();
	const productId = $(this).attr("value");
	$.ajax("cart/",{
		method:"POST",
		data: {"productId":productId,
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

function addMultipleProduct(event){
	event.preventDefault();
	const productId = $(this).attr("value");
	const quantity = $("#inputQty").val();
	$.ajax("/cart/",{
		method:"POST",
		data: {"productId":productId,
			   "quantity": quantity},
		dataType: "json"
	})
	.done(function(response){
		$('#itemCount').text(response.cardSize);
		showSuccess(response.message);
		$('#inputQty').val(1);
	})
	.fail(function(xhr, textStatus, errorThrown){
		let errorObj = JSON.parse(xhr.responseText);
		showError(errorObj.message);
	})
}

