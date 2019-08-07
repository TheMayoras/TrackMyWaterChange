var popoverInfo = {
    title: 'Password Warning!',
    content: 'I only user self-signed certificates!\nPlease do not use your normal password'
};

$(function () {
    $("#password").popover(popoverInfo)
        .blur(function () {
            $(this).popover('hide');
        })
        .focus(function () {
            $(this).popover('show');
        })
});

$(function () {
    $("form").submit(function (e) {
        if (!passwordsMatch()) {
            shakeField("#password");
            shakeField("#confirmationPassword");
            e.preventDefault();
        }
        
    })
});

function passwordsMatch() {
    console.log($("#password").val() === $("#confirmationPassword").val());
    return $("#password").val() === $("#confirmationPassword").val();
}

function shakeField(fieldName) {
    $(fieldName).effect("shake", {
        distance: 5,
        direction: "left",
        times: 2
    });
}