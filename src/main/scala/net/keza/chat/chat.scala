package net.keza.chat

import org.scalatra._
import scalate.ScalateSupport

class chat extends ScalatraServlet with ScalateSupport {

	var message = ""

  get("/") {
    <html>
      <body>
        <h1>Scala Chat</h1>
        <form action="/chatnick" method="post">
        	<label>nick: <input name="nick"></input></label>
        	<input type="submit"></input>
        </form>
      </body>
    </html>
  }

  post("/chatnick") {
  	redirect("/chat")
  }

	get("/chatcontent") {
		var temp = message
		message = ""
		temp
	}

	get("/chat") {
    <html>
    	<head>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
        <script type="text/javascript" src="js/chat.js"></script>
    	</head>
      <body>
        <h1>Chat</h1>
        <div id="messages">
        </div>
        <form action="/chatmessage" method="post">
       		<label>message: <input id="message" name="message"></input></label>
        	<input type="button" value="send" id="send"></input>
       	</form>
      </body>
    </html>
	}
	
  post("/chatmessage") {
  	message = params("message")
  	redirect("/chat")
  }

  notFound {
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound() 
  }
}
