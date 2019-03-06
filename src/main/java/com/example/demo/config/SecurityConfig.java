package com.example.demo.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
	
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .antMatchers( "*/all").hasAnyRole("ANONYMOUS, USER")
            .anyRequest()
            //.permitAll()
            .fullyAuthenticated()
            //.authenticated()
            .and()
     /*   .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
        .logout()*/
            //.permitAll();
            .httpBasic();
		http.csrf().disable();
	}
	

	    /*@Bean
	    @Override
	    public UserDetailsService userDetailsService() {
	        UserDetails user =
	             User.withDefaultPasswordEncoder()
	                .username("alu")
	                .password("123456")
	                .roles("USER")
	                .build();

	        return new InMemoryUserDetailsManager(user);
	    }*/


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth.inMemoryAuthentication()
		.withUser("alu").password(passwordEncoder().encode("user1pass")).roles("USER")
		.and()
		.withUser("abi").password("123456").roles("ADMIN");
		
	};
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
