package alura.challenge.forohub.infrastructure.adapter.in.rest.filter;

import alura.challenge.forohub.application.service.AuthenticateUserService;
import alura.challenge.forohub.application.service.TokenService;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final AuthenticateUserService authenticateUserService;
    private final HandlerExceptionResolver exceptionResolver;

    @Autowired
    public SecurityFilter(TokenService tokenService, AuthenticateUserService authenticateUserService,@Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.tokenService = tokenService;
        this.authenticateUserService = authenticateUserService;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authenticationHeader = request.getHeader("Authorization");

        try {
            if(authenticationHeader != null){
                var token = authenticationHeader.replace("Bearer","").trim();
                   var username = tokenService.getSubject(token);

               if(!username.isBlank()) {
                   var user = authenticateUserService.loadUserByUsername(username);
                   var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                   SecurityContextHolder.getContext().setAuthentication(authentication);
               }
            }
            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException(e);
        } catch (TokenExpiredException e){
        exceptionResolver.resolveException(request,response,null,e);
        }
    }
}
