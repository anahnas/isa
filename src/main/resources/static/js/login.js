var urlLog = "/logUser";

$(document).ready(function(){

    $(document).on('submit', "#logForm", function(e){
        e.preventDefault();
        var $email = $('#email_address');
        var $password = $('#password');


        var userDTO = JSON.stringify({
            email: $email.val(),
            password: $password.val()
        });

        alert('IMA LI ME');


        $.ajax({
            type: 'POST',
            url: urlLog,
            contentType : 'application/json',
            dataType : "json",
            data : userDTO,
            success: [function(message){
                    alert("You have logged in successfully.");
                    document.location.href = "/homepage.html";
            }],
            error: function(xhr, status, error) {
                if (xhr.responseText!=='true'){
                    alert(xhr.responseText);
                }
            }
        })


    })


});