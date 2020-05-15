"use strict";
$(function(){
	$('.addToCard').on('click',addProduct);
	$('#addToCard').on('click',addMultipleProduct);
});

function addProduct(event){
	event.preventDefault();
	var token = $("#_csrf").attr("content");
	var header = $("#_csrf_header").attr("content");
	const productId = $(this).attr("value");
	$.ajax("cart/",{
		beforeSend:function(xhr) {
			xhr.setRequestHeader(header, token);
		},
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
		try{
			let errorObj = JSON.parse(xhr.responseText);
			if(errorObj.message)
				showError(errorObj.message);
			else showError("Unknown error happened!");
		}catch(e){
			showError("Unknown error happened!");
		}
	})
}

function addMultipleProduct(event){
	event.preventDefault();
	var token = $("#_csrf").attr("content");
	var header = $("#_csrf_header").attr("content");
	const productId = $(this).attr("value");
	const quantity = $("#inputQty").val();

	$.ajax("/cart/",{
		beforeSend:function(xhr) {
			xhr.setRequestHeader(header, token);
		},
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
		try{
			let errorObj = JSON.parse(xhr.responseText);
			if(errorObj.message)
				showError(errorObj.message);
			else showError("Unknown error happened!");
		}catch(e){
			showError("Unknown error happened!");
		}
	})
}

