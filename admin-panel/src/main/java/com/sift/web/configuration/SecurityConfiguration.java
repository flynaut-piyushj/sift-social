package com.sift.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends  WebSecurityConfigurerAdapter {


    @Autowired
    private PasswordEncoder PasswordEncoder;

    @Autowired
	@Qualifier("userService")
	private UserDetailsService userDetailsService;
    
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(PasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		http.authorizeRequests()
		  .antMatchers("/resources/**").permitAll()
		  .antMatchers("/dashboard/**").hasAuthority("ROLE_APP_USER")
		  .anyRequest().permitAll()
		  .and()
		    .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/dashboard", true)
		  .and()
		    .logout().logoutSuccessUrl("/login?logout") 
		   .and()
		   .exceptionHandling().accessDeniedPage("/403")
		  .and()
		    .csrf();
		
		// for logout page
		/*http.authorizeRequests()
		  .antMatchers("/society/**").hasAuthority("Society_Admin")  
		  .anyRequest().permitAll()
		  .and()
		    .formLogin().loginPage("/logout").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/society/dashboard")
		  .and()
		    .logout().logoutSuccessUrl("/login?logout") 
		   .and()
		   .exceptionHandling().accessDeniedPage("/403")
		  .and()
		    .csrf();*/
}

}
