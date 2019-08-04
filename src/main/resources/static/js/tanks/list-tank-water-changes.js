$(function() {
    $("form#delete-water-change").submit(function (e) {
        if (!confirm("Are you sure you want to delete this water change?")) {
            e.preventDefault();
        }
    })
});
