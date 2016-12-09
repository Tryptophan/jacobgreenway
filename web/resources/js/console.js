const BACKSPACE = 8;
const ENTER = 13;

var prompt = $(".prompt");
var input = $(".input");
var logged = $(".logged");

$(document).keypress(function (event) {
    var text = input.text().toString();
    var response = "";

    if (event.keyCode == ENTER) {

        response = getCommandResponse(text).responseText;

        // Reset input -> move prompt & input to logged
        response = prompt.text() + " " + input.text() + "<br>" + response;

        logged.append(response + "<br>");

        input.text("");
    }
    else {
        input.text(text + String.fromCharCode(event.keyCode));
    }
});

function getCommandResponse(command) {
    return $.ajax({
        url: "/console",
        type: "GET",
        async: false,
        headers: {"command": command}
    }).done(function(data) {
        return data;
    });
}

$(document).keydown(function(event) {
    var text = input.text().toString();

    if (event.keyCode == BACKSPACE && text.length > 0) {
        input.text(text.substr(0, text.length - 1));
    }
});

$(document).ready(function(){
    var response;
    response = getCommandResponse("name").responseText;
    logged.append(response + "<br>");

    response = getCommandResponse("help").responseText;
    logged.append(response + "<br>");
});

var cursor = $(".cursor");
var interval = setInterval(function() {
   cursor.toggleClass("blink");
}, 500);