"use strict";
document.addEventListener("DOMContentLoaded", function(event) {
    for (var i = 1; i <= 12; i++)
    {
        $('#monthpicker').append($('<option />').val(i).html(i));
    }
    for (var i = new Date().getFullYear(); i <= 2040; i++)
    {
        $('#yearpicker').append($('<option />').val(i).html(i));
    }
});
