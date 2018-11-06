package com.multi.gradle.module.web.security;

import com.multi.gradle.module.core.utils.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    @Autowired
    TokenService token;

    // Header에서 토큰을 가져온다..
    private String getToken(HttpServletRequest request){
        System.out.println("323");
        String authHeader = request.getHeader(AUTH_HEADER);
        if ( authHeader != null && authHeader.startsWith("Bearer ")){
            System.out.println(authHeader);
            return authHeader.substring(7);
        }
        return null;
    }

    @Override // Filtering이 실행되는곳
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("111");
        String error = "";
        String authToken = getToken( request );
        if (authToken == null) {
            // Get username from token
            System.out.println("token is null ");
        }else{
            System.out.println("token is not null ");
        }
        String userData = "";
        if(token.chkToken(authToken) != null){
            userData = token.chkToken(authToken);
        }else{
            error = "token Error ";
        }
        chain.doFilter(request, response);
    }
}
