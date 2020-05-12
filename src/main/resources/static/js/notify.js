/**
 * Author:Munkh-Erdene Tolya
 * Description: Notification dialog
 */

function showSuccess(msg){
    const dialog = $('#notify');
    dialog.css("background-color", "#6c757d");
    dialog.text(msg);
    dialog.show();
    setTimeout(function() {
        dialog.fadeOut('fast');
    }, 1500);
}

function showError(msg){
    const dialog = $('#notify');
    dialog.text(msg);
    dialog.css("background-color", "#dc3545");
    dialog.show();
    setTimeout(function() {
        dialog.fadeOut('fast');
    }, 1500);
}
