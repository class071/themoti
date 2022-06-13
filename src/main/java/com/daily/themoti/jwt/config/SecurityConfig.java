package com.daily.themoti.jwt.config;

import com.daily.themoti.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/login/**", "/user/kakao/oauth").permitAll()
                        .antMatchers(HttpMethod.POST, "/refresh").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/userinfo").authenticated()
                        .antMatchers(HttpMethod.POST, "/api/smoke-amount").authenticated()
                        .antMatchers(HttpMethod.GET, "/api/smoke-amount/**").authenticated()
                        .antMatchers(HttpMethod.POST, "/api/post").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/api/post/{postId}").authenticated()
                        .antMatchers(HttpMethod.PUT, "/api/post/{postId}").authenticated()
                        .antMatchers(HttpMethod.GET, "/api/post/{postId}", "/api/post").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/reply").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/api/reply/{replyId}").authenticated()
                        .antMatchers(HttpMethod.GET, "/api/reply/{postId}").permitAll()
                        .antMatchers(HttpMethod.GET, "/area/all", "/area/{area}").permitAll()
                        .antMatchers(HttpMethod.POST, "/area/add").authenticated()
                .and()
                    .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                    .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(tokenService, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
}
