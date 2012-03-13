$(function () {
	setInterval(function () {
		$.get("/chatcontent").complete(function (data) {
			if (data.responseText) {
				$("#messages").append("<p>"+data.responseText+"</p>");
			}
		});
	}, 1000);
	
	$("#send").click(function () {
		$.post("/chatmessage", { "message": $("#message").val() });
		$("#message").val("");
	});
});

