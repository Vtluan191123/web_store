package com.shop.vtluan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.shop.vtluan.service.CustomUserDetailsServer;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

        @Bean
        public UserDetailsService userDetailsService() {
                return new CustomUserDetailsServer();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(userDetailsService());
                provider.setPasswordEncoder(passwordEncoder());
                return provider;
        }

        @Bean
        public AuthenticationSuccessHandler CustomSuccesshandle() {
                return new CustomSuccesshandle();
        }

        // @Bean
        // public SpringSessionRememberMeServices rememberMeServices() {
        // SpringSessionRememberMeServices rememberMeServices = new
        // SpringSessionRememberMeServices();
        // rememberMeServices.setAlwaysRemember(true);
        // return rememberMeServices;
        // }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                // .csrf(csrf -> csrf.disable())
                                .csrf(csrf -> csrf
                                                .ignoringRequestMatchers("register") // Bỏ qua CSRF cho đường dẫn
                                                                                     // /register
                                )
                                .authorizeHttpRequests(authorize -> authorize

                                                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE)
                                                .permitAll()
                                                .requestMatchers("/login", "/logout", "/register",
                                                                "/admin/css/**",
                                                                "/",
                                                                "/cart",
                                                                "/forgot_password",
                                                                "/shop",
                                                                "/send_email",
                                                                "/product_detail/**",
                                                                "/admin/js/**",
                                                                "/admin/lib/**",
                                                                "/client/css/**",
                                                                "/client/image/**",
                                                                "/client/js/**",
                                                                "/client/lib/**",
                                                                "/WEB-INF/views/**")
                                                .permitAll()
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                .anyRequest().authenticated())

                                .exceptionHandling(ex -> ex.accessDeniedPage("/access-deny"))
                                .formLogin(login -> login
                                                .successHandler(CustomSuccesshandle())
                                                .loginPage("/login")
                                                .failureUrl("/login?error"))
                                .rememberMe(rm -> rm.key("uniqueAndSecretKey")
                                                .userDetailsService(userDetailsService())
                                                .tokenValiditySeconds(86400)
                                // .rememberMeServices(rememberMeServices())
                                )
                                .sessionManagement(ssm -> ssm
                                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                                                .maximumSessions(1)
                                                .maxSessionsPreventsLogin(false))
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")
                                                .invalidateHttpSession(false)
                                                .deleteCookies("JSESSIONID")
                                                .permitAll());

                return http.build();
        }
}