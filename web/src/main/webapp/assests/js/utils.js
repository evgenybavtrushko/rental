
function checkAvailability() {
    jQuery.ajax({
        url: contextUrl +"/frontController?command=checkUserExistController",
        data:'login='+$("#login").val(),
        type: "POST",
        success:function(data){
            $("#user-availability-status").html(data);},
        error:function (){}
    });
}