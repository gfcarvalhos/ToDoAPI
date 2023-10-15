package com.gabrielsilva.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gabrielsilva.todolist.services.UserServices;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  private UserServices service;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if(servletPath.startsWith("/tasks/")){
          // Pegar autenticação (usuario e senha)
          var authorization = request.getHeader("Authorization").substring("Basic".length()).trim();
      
          byte[] authDecode = Base64.getDecoder().decode(authorization);
          String[] authString = new String(authDecode).split(":");
          String username = authString[0];
          String password = authString[1];
      
          //Validar usuario
          var user = service.findUser(username);
          if(user == null){
            response.sendError(401);
          } else {
            //Validar senhar
            var userPassword = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if(userPassword.verified){
              request.setAttribute("idUser", user.getId());
              filterChain.doFilter(request, response);
            } else{
              response.sendError(401);
            }
          }
        } else{
          filterChain.doFilter(request, response);
        }
  }
}
