package com.topcheer.tools.security;

import com.topcheer.tools.common.MD5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService service(){
        return new MySecurityService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(service()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Util.encode((String)charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                System.out.println(charSequence+"###"+s);
                System.out.println(MD5Util.encode((String)charSequence));
                return s.equals(MD5Util.encode((String)charSequence));
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .exceptionHandling().authenticationEntryPoint(new MyJsonAuthorizedEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/","/third/**","/css/**","/js/**","/img/**","/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds(201600)
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf();
    }
}
