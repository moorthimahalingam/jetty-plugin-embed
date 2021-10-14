package test.jetty.embed.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Greeting")
public class GreetingServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws IOException, ServletException {
    System.out.println("auth token servlet ** " + servletRequest.getHeader("Authorization"));
    servletResponse.setContentType("text/html");
    PrintWriter writer = servletResponse.getWriter();
    writer.println("Welcome to servlet" + servletResponse.getStatus());
    writer.close();
  }

  @Override
  protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws IOException, ServletException {
    doGet(servletRequest, servletResponse);
  }
}
