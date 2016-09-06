$(document).ready(function () {
    updateTweets();

    $("#js-sendtweet").on("click", function () {
        sendTweet()
    });
});

function sendTweet() {
    $input = $("#js-tweet-text");
    var text = $input.val();
    if (text.length == 0) {
        return;
    }
    $.ajax({
        url: "/tweets/add",
        type: "POST",
        data: {
            text: text
        },
        success: function () {
            updateTweets();
        }
    })
}

function updateTweets() {
    $.ajax({
        url:"tweets/getAll",
        type:"GET",
        dataType: "html",
        success: function(data) {
            $("#js-tweets").html(data);
        }
    })
}