"use strict";
$(function(){
	$('.addToCard').on('click',addProduct);
	$('#addToCard').on('click',addMultipleProduct);

	$(".btnPrint").on("click", function () {

		// var doc = new jsPDF();
		var printWindow = window.open('', '', 'height=800,width=800');
		printWindow.document.write('<html><head><title>Order Detail</title>');
		printWindow.document.write('<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">');
		printWindow.document.write('</head><body >');
		printWindow.document.write($(this).parent().closest('.card').html());
		printWindow.document.write('</body></html>');
		printWindow.document.close();
		printWindow.print();
		setTimeout(function(){printWindow.close();},10);
		return true;
	});
	var specialElementHandlers = {
		'#editor': function (element, renderer) {
			return true;
		}
	};
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

