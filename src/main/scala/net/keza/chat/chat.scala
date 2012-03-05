package net.keza.chat

import org.scalatra._
import scalate.ScalateSupport

class chat extends ScalatraServlet with ScalateSupport {

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
    <html>
      <body>
        <h1>Nickname</h1>
        {params("nick")}
      </body>
    </html>
  }

  notFound {
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound() 
  }
}
