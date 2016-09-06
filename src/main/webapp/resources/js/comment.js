$(document).ready(function () {
    updateComments();

    $("#js-sendcomment").on("click", function () {
        sendComment()
    });
});

function sendComment() {
    $input = $("#js-comment-text");
    var text = $input.val();
    if (text.length == 0) {
        return;
    }
    $.ajax({
        url: "/comments/add",
        type: "POST",
        data: {
            text: text
        },
        success: function () {
            updateComments();
        }
    })
}

function updateComments() {
    $.ajax({
        url:"/tweets/"+userId+"/comments",
        type:"GET",
        dataType: "html",
        success: function(data) {
            $("#js-comments").html(data);
        }
    })
}