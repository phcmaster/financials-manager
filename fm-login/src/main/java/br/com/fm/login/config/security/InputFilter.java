package br.com.fm.login.config.security;

import br.com.fm.login.service.impl.LoginService;
import br.com.fm.mongodb.entity.UserEntity;
import br.com.fm.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class InputFilter extends OncePerRequestFilter {


    private LoginService loginService;

    private UserRepository userRepository;


    @Autowired
    public InputFilter(LoginService loginService, UserRepository userRepository) {
        this.loginService = loginService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = retrieveToken(httpServletRequest);

        boolean validToken = loginService.isTokenValid(token);

        if (validToken) {
            verifyClientAutentication(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private void verifyClientAutentication(String token) {

        String userId = loginService.tokenGetUserId(token);

        Optional<UserEntity> user = userRepository.findById(userId);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String retrieveToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());


    }


}
