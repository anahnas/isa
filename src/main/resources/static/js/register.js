var urlReg = "/registerUser";

$(document).ready(function(){

    $(document).on('submit', "#regForm", function(e){
        e.preventDefault();
        var $firstName = $('#f_name');
        var $lastName = $('#l_name');
        var $email = $('#email_address');
        var $address = $('#address');
        var $password = $('#password');
        var $password2 = $('#password2');

        if($password.val() !== $password2.val()){
            alert("Different passwords!");
            return false;
        }


        var user = JSON.stringify({
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            email: $email.val(),
            address: $address.val(),
            password: $password.val()
        });

        alert('IMA LI ME');


        $.ajax({
            type: 'POST',
            url: urlReg,
            contentType : 'application/json',
            dataType : "json",
            data : user,
            success: [function(newUser){
                alert("You have registered successfully.");
                document.location.href = "/";
            }],
            error: function(xhr, status, error) {
                if (xhr.responseText!=='true'){
                    alert(xhr.responseText);
                }
            }
        })


    })


});