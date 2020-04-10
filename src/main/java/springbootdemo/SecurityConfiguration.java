package springbootdemo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import springbootdemo.filters.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		//USING IN-MEMORY AUTHENTICATION
		/*auth.inMemoryAuthentication()
		.withUser("user")
		.password("password")
		.roles("USER")
		.and()
		.withUser("sravanthi")
		.password("konreddy")
		.roles("USER")
		.and()
		.withUser("admin")
		.password("admin")
		.roles("ADMIN")
		.and()
		.withUser("foo")
		.password("foo")*/
		
		//USING JPA AUTHENTICATION
		
		/*auth.jdbcAuthentication()
		.dataSource(dataSource)
		.withDefaultSchema()
		.withUser(
				User.withUsername("user")
				.password("pass")
				.roles("USER")
				)
		.withUser(
				User.withUsername("admin")
				.password("admin")
				.roles("ADMIN")
				);*/
		
		//USING JPA AUTHENTICATION
		
		System.out.println("USER DETAILS IN SECURITY CONFIGURATION::");
		
		auth.userDetailsService(userDetailsService);
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		/*
		 * This is to let you deal with clear text password..ideally not encouraged to
		 * do
		 */
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub

		/*http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("ADMIN","USER")
		.antMatchers("/user").hasRole("USER")
		.antMatchers("/").permitAll()
		.and().formLogin();*/
		
		http.csrf().disable()
		.authorizeRequests().antMatchers("/authenticate","/registerUser").permitAll()
		.anyRequest().authenticated().and().cors()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

}
