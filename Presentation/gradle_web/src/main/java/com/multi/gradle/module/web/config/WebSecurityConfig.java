package com.multi.gradle.module.web.config;

import com.multi.gradle.module.core.utils.TokenService;
import com.multi.gradle.module.web.security.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static java.util.Arrays.asList;

@Configuration
@EnableWebSecurity  // @EnableWebSecurity 웹보안을 활성화 한다. 하지만 그자체로는 유용하지 않고,
                    // 스프링 시큐리티가 WebSecurityConfigurer를 구현하거나 컨텍스트의 WebSebSecurityConfigurerAdapter를 확장한 빈으로 설정되어야 한다.
                    // 하지만 WebSebSecurityConfigurerAdapter를 확장하여 클래스를 설정하는 것이 가장 편하고 자주 쓰이는 방법이다.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    TokenService tokenService;

  /*  @Bean // 액션시 Filter로 토큰 체크를 함
    public TokenAuthenticationFilter tokenAuthenticationFilter(){
        System.out.println("토큰 Bean");
        return new TokenAuthenticationFilter();
    }
*/
    @Override // Security Custom
    protected void configure(HttpSecurity http) throws Exception {
        /**
          * @보안필터체인
          *  1. Authentication 로딩: SecurityContextPersistenceFilter
          *  2. 로그아웃 요청 처리 : LogoutFilter
          *  3. 인증 요청 처리     : UsernamePasswordAuthenticationFilter
          *  4. 로그인 폼 출력     : DefaultLoginPageGenerationFilter
          *  5. 임의 사용자 처리   : AnonymousAuthenticationfilter
          *  6. 익셉션 처리        : ExceptionTranslationFilter
          *  7. 접근 권한 검사     : FilterSecurityInterceptor
          **/
        // 이때는 생성자를 안 만들어도된다.
        //http.antMatcher("/**").addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.antMatcher("/**").addFilterBefore(new TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
    }
    /*@Override // Security Custom
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "/"
        );
        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/"
        );
    }*/
}
/**
  * @EnableWebSecurity
  * configure (AuthenticationManagerBuilder)
  *       AuthenticationProviders를 쉽게 추가 할 수있게하여 인증 메커니즘을 설정하는 데 사용됩니다.
  *       다음은 내장 된 'user'및 'admin'로그인을 사용하여 메모리 내 인증을 정의합니다.
  *        public void configure(AuthenticationManagerBuilder auth) {
  *            auth
  *                .inMemoryAuthentication()
  *                .withUser("user")
  *                .password("password")
  *                .roles("USER")
  *            .and()
  *                .withUser("admin")
  *                .password("password")
  *                .roles("ADMIN","USER");
  *        }
  *
  * configure (HttpSecurity)
  *       선택 일치를 기반으로 리소스 수준에서 웹 기반 보안을 구성 할 수 있습니다.
  *       아래 예제에서는 / admin /으로 시작하는 URL을 ADMIN 역할이있는 사용자로 제한하고다른 URL을 성공적으로 인증해야한다고 선언합니다.
  *
  * protected void configure(HttpSecurity http) throws Exception {
  *     http
  *         .authorizeRequests()
  *         .antMatchers("/admin/**").hasRole("ADMIN")
  *         .anyRequest().authenticated()
  * }
  *
  * configure (WebSecurity)
  *      전역 보안 (리소스 무시, 디버그 모드 설정, 사용자 지정 방화벽 정의 구현을 통한 요청 거부)에 영향을주는 구성 설정에 사용됩니다.
  *     예를 들어, 다음 방법을 사용하면 인증을 위해 "/ resources /"로 시작하는 모든 요청이 무시됩니다.
  *
  *     public void configure(WebSecurity web) throws Exception {
  *         web
  *             .ignoring()
  *             .antMatchers("/resources/**");
  *     }
  **/
/** @EnableWebMvcSecurity   애너테이션은 스프링 MVC 인수 결정자를 설정하여, 핸들러 메소드가 @AuthenticationPrincipal 애너테이션이 붙은 인자를 사용하여 인증한 사용자 주체를 받는다.
  *                             또한 자동으로 숨겨진 사이트 간 요청 위조(CSRF, Cross-Site Request Forgery) 토큰필드(token field)를 스프링의 폼 바인딩 태그 라이브러리를 사용하여 추가하는 빈을 설정한다.
  *                             WebSebSecurityConfigurerAdapterWebSebSecurityConfigurerAdapter는 세가지 configure() 메소드를 오버라이딩하고 동작을 설정하는 것으로 웹 보안을 설정할 수 있다.
  *        메소드                                            설명
  * configure(WebSecurity)                   스프링 시큐리티의 필터 연결을 설정하기 위한 오버라이딩이다.
  * configure(HttpSecurity)                  인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위한 오버라이딩이다.
  * configure(AuthenticationManagerBuilder)  사용자 세부 서비스를 설정하기 위한 오버라이딩이다.
  **/
/**
  * Method and Description
  *
  * addFilter(javax.servlet.Filter filter)
  *   Adds a Filter that must be an instance of or extend one of the Filters provided within the Security framework.
  *   보안 프레임워크에서 제공되는 필터중 하나의 인스턴스 거나 그중 하나를 확장해야하는 필터를 추가합니다.
  * addFilterAfter(javax.servlet.Filter filter, java.lang.Class<? extends javax.servlet.Filter> afterFilter)
  *   Allows adding a Filter after one of the known Filter classes.
  *   알려진 Filter 클래스 중 하나 뒤에 필터를 추가 할 수 있습니다.
  * addFilterAt(javax.servlet.Filter filter, java.lang.Class<? extends javax.servlet.Filter> atFilter)
  *   Adds the Filter at the location of the specified Filter class.
  *   지정된 Filter 클래스의 위치에 Filter를 추가 합니다.
  * addFilterBefore(javax.servlet.Filter filter, java.lang.Class<? extends javax.servlet.Filter> beforeFilter)
  *   Allows adding a Filter before one of the known Filter classes.
  *   알려진 Filter 클래스 중 하나 앞에 Filter를 추가 할 수 있습니다.
  * anonymous()
  *   Allows configuring how an anonymous user is represented.
  *   익명 사용자의 표현 방식을 구성 할 수 있습니다.
  * antMatcher(java.lang.String antPattern)
  *   Allows configuring the HttpSecurity to only be invoked when matching the provided ant pattern.
  *   제공된 ant패턴과 일치 할 경우 HttpSecurity를 호출하도록 구성 할 수 있습니다.
  * authenticationProvider(AuthenticationProvider authenticationProvider)
  *   Allows adding an additional AuthenticationProvider to be used
  *   사용할 추가 AuthenticationProvider를 추가 할 수 있습니다.
  * authorizeRequests()
  *   Allows restricting access based upon the HttpServletRequest using
  *   다음을 사용하여 HttpServletRequest를 기반으로 엑세스를 제한 할 수 있습니다.
  * beforeConfigure()
  *   Invoked prior to invoking each SecurityConfigurer.configure(SecurityBuilder) method.
  *   각 SecurityConfigurer.configure (SecurityBuilder) 메서드를 호출하기 전에 호출됩니다.
  * cors()
  *   Adds a CorsFilter to be used.
  *   사용할 CorsFilter를 추가합니다.
  * csrf()
  *   Adds CSRF support.
  *   CSRF 지원을 추가합니다.
  * exceptionHandling()
  *   Allows configuring exception handling.
  *   예외 처리를 구성 할 수 있습니다.
  * formLogin()
  *   Specifies to support form based authentication.
  *   폼 기반 인증을 지원하도록 지정합니다.
  * headers()
  *   Adds the Security headers to the response.
  *   응답에 보안 헤더를 추가합니다.
  * httpBasic()
  *   Configures HTTP Basic authentication.
  *   HTTP 기본 인증을 구성합니다.
  * jee()
  *   Configures container based pre authentication.
  *   컨테이너 기반 사전 인증을 구성합니다.
  * logout()
  *   Provides logout support.
  *   로그 아웃 지원을 제공합니다.
  * mvcMatcher(java.lang.String mvcPattern)
  *   Allows configuring the HttpSecurity to only be invoked when matching the provided Spring MVC pattern.
  *   제공된 Spring MVC 패턴을 일치시킬 때만 HttpSecurity를 ​​호출하도록 구성 할 수 있습니다.
  * openidLogin()
  *   Allows configuring OpenID based authentication.
  *   OpenID 기반 인증을 구성 할 수 있습니다.
  * performBuild()
  *   Subclasses must implement this method to build the object that is being returned.
  *   서브 클래스는이 메소드를 구현하여 리턴되는 오브젝트를 빌드해야합니다.
  * portMapper()
  *   Allows configuring a PortMapper that is available from AbstractConfiguredSecurityBuilder.getSharedObject(Class).
  *   AbstractConfiguredSecurityBuilder.getSharedObject (Class)에서 사용할 수있는 PortMapper를 구성 할 수 있습니다.
  * regexMatcher(java.lang.String pattern)
  *   Allows configuring the HttpSecurity to only be invoked when matching the provided regex pattern.
  *   제공된 정규식 패턴을 일치시킬 때만 HttpSecurity를 ​​호출하도록 구성 할 수 있습니다.
  * rememberMe()
  *   Allows configuring of Remember Me authentication.
  *   Remember Me 인증 구성을 허용합니다.
  * requestCache()
  *   Allows configuring the Request Cache.
  *   요청 캐시 구성을 허용합니다.
  * requestMatcher(RequestMatcher requestMatcher)
  *   Allows configuring the HttpSecurity to only be invoked when matching the provided RequestMatcher.
  * requestMatchers()
  *   Allows specifying which HttpServletRequest instances this HttpSecurity will be invoked on.
  *   이 HttpSecurity가 호출 될 HttpServletRequest 인스턴스를 지정하도록 허용합니다.
  * requiresChannel()
  *   Configures channel security.
  *   채널 보안을 구성합니다.
  * securityContext()
  *   Sets up management of the SecurityContext on the SecurityContextHolder between HttpServletRequest's.
  *   HttpServletRequest 사이의 SecurityContextHolder에서 SecurityContext의 관리를 설정합니다.
  * servletApi()
  *   Integrates the HttpServletRequest methods with the values found on the SecurityContext.
  *   HttpServletRequest 메서드를 SecurityContext에있는 값과 통합합니다.
  * sessionManagement()
  *   Allows configuring of Session Management.
  *   세션 관리를 구성 할 수 있습니다.
  * setSharedObject(java.lang.Class<C> sharedType, C object)
  *   Sets an object that is shared by multiple SecurityConfigurer.
  *   여러 SecurityConfigurer가 공유하는 개체를 설정합니다.
  * userDetailsService(UserDetailsService userDetailsService)
  *   Allows adding an additional UserDetailsService to be used
  *   사용할 추가 UserDetailsService를 추가 할 수 있습니다.
  * x509()
  *   Configures X509 based pre authentication.
  *   X509 기반 사전 인증을 구성합니다.
  **/