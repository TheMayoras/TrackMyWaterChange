var popoverInfo = {
    title: 'Now Using Let\'s Encrypt!',
    content: 'The site now uses certificates issued by Let\'s Encrypt!\nFeel free to use whatever password!\nYour password is encrypted at rest'
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