$(function () {
    $("#password").blur(
        function () {
            console.log("Password");
            if ($(this).val().length > 0 && $(this).val().length < 8) {
                $(this).effect("shake", { direction: "left", times: 4, distance: 7 });
            }
        }
    )
});

$(function () {
    $("form").submit(function (e) {
        if (checkFormErrors()) {
            e.preventDefault();
            $("#submit-errors").text("Fields not filled out").addClass("alert alert-danger").show().fadeOut(1000);
        }
    });
})



function checkFormErrors() {
    let errors = false;

    errors = $("#username").val().length === 0;
    errors = errors || $("#password").val().length < 8;

    return errors;
}

