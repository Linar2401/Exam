var nextPage = 1;
var chapterId = $("#chapterId").val();

// $("#nextComments").click(f());
$(document).ready(function(){
    $("#nextComments").click(function () {
        $.get("/comment/" + chapterId + "/" + nextPage,function (data) {
            nextPage = nextPage+ 1;
            var dto = data;
            var comments = dto.comments;
            if (dto.nextComment == false){
                $("#nextComments").addClass("hidden");
            }
            comments.forEach(comment => $("#comments").append("<div class=\"card my-1\">\n" +
                "                            <div class=\"card-header\">\n" +
                "                                <a href=\"/profile/" + comment.user.id + "\"</a>\n" +
                "                            </div>\n" +
                "                            <div class=\"card-body\">\n" +
                "                                <p class=\"card-text\">" + comment.text + "</p>\n" +
                "                            </div>\n" +
                "                            <div class=\"card-footer text-muted\">\n" + comment.createdAt + "</div>\n</div>"))
        })
    });
    $("#test2").click(function () {
        alert("test2")
    });
});
function f() {
    alert()
}