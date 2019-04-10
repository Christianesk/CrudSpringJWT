package com.proyecto.springboot.app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.proyecto.springboot.app.auth.filter.JWTAuthenticationFilter;
import com.proyecto.springboot.app.auth.filter.JWTAuthorizationFilter;
import com.proyecto.springboot.app.auth.handler.LoginSuccessHandler;
import com.proyecto.springboot.app.auth.service.JWTService;
import com.proyecto.springboot.app.models.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
//	@Autowired
//	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/css/**","/js/**","/images/**","/listar","/login/**","/locale","/listar-rest-api").permitAll()
		.anyRequest().authenticated()
//		.and()
//	    .formLogin()
//	    .successHandler(successHandler)
//	    .loginPage("/login")
//	    .permitAll()
//	    .and()
//	    .logout().permitAll()
//	    .and()
//	    .exceptionHandling()
//	    .accessDeniedPage("/error_403")
	    .and()
	    .addFilter(new JWTAuthenticationFilter(authenticationManager(),jwtService))
	    .addFilter(new JWTAuthorizationFilter(authenticationManager(),jwtService))
	    .csrf().disable()
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		
		//con jpa
		
		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
		
	}
}
