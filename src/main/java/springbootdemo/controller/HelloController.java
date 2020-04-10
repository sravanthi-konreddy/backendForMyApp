package springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootdemo.MyUserDetailsService;
import springbootdemo.model.AuthenticationRequest;
import springbootdemo.model.AuthenticationResponse;
import springbootdemo.util.JwtUtil;

@RestController
public class HelloController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired 
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping("/hello")
	public String welcome()
	{
		return "You are awesome!!";
	}
	
	@RequestMapping("/")
	public String home()
	{
		return "Welcome!!";
	}
	
	@RequestMapping("/user")
	public String user()
	{
		return "Welcome User!!!";
	}
	
	@RequestMapping("/admin")
	public String admin()
	{
		return "Welcome Admin!!!";
	}
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?>createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception
	{
		try {
			
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
	
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username and password"+e);
		}
		System.out.println("BEFORE CALLING LOADBYUSERNAME");
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt=jwtTokenUtil.generateToken(userDetails);
		
		//return ResponseEntity.ok(new AuthenticationResponse(jwt));
		return ResponseEntity.ok().body(jwt);
	}

}
 