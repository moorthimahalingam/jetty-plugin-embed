package test.jetty.embed.filter;

import java.io.IOException;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter
public class AuthFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest servletRequest = (HttpServletRequest) request;
    HttpServletResponse servletResponse = (HttpServletResponse) response;

    String token = servletRequest.getHeader("Authorization");

    if (token != null) {
      System.out.println("Inside the filter method ");
      int status = HttpServletResponse.SC_OK;
      try {
        DecodedJWT jwt = JWT.decode(token);
        System.out.println("Expire time " + jwt.getExpiresAt());
        if (jwt.getExpiresAt().before(new Date())) {
          status = HttpServletResponse.SC_UNAUTHORIZED;
        }

      } catch (Exception e) {
        System.err.println("Error while decrypting token ");
        status = HttpServletResponse.SC_FORBIDDEN;
        response.reset();
      } finally {
        servletResponse.reset();
        servletResponse.setStatus(status);
        response = servletResponse;
      }
    }
    System.out.println("Status :: " + servletResponse.getStatus());
    chain.doFilter(request, response);
  }
}
