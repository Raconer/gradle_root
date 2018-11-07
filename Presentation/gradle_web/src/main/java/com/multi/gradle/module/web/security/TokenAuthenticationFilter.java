package com.multi.gradle.module.web.security;

import com.multi.gradle.module.core.utils.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    TokenService token;

    public  TokenAuthenticationFilter(TokenService token){
        this.token = token;
    }


    // 필터가  favicon.ico 에서도 실행되니 초반 필터는 favicon.icon + x 이다.
    @Override // Filtering이 실행되는곳
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String error = "";
        String authToken = token.getToken(request);
        if (authToken == null) {
            // Get username from token
            System.out.println("token is null : " + request.getServletPath() );
        }else{
            System.out.println("token is not null : " + request.getServletPath());
            String userData = "";
            if(token.chkToken(authToken) != null){
                userData = token.chkToken(authToken);
                request.setAttribute("userData", userData);
            }else{
                error = "token Error ";
                request.setAttribute("error", error);
            }
        }
        chain.doFilter(request, response);
        // Filter는 request할때 한번, response할때 한번 동작한다.

        // 필터 메서드 내용부의 마지막 코드는 현재까지 작업한 내용을 적용하고 연결된 페이지로 이동하도록 만들어 준다.
        // 이런 역할을 하는 메서드가 chain 객체의 doFilter()이다

        // 세번째 매개변수인 FilterChain 클래스의 객체인
        // chain을 이용해서 다른 필터나 서블릿과 연결하는 코드를 반드시 작성해야 한다.
    }
}
