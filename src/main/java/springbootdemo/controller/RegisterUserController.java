package springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootdemo.model.RegisterResponse;
import springbootdemo.model.User;
import springbootdemo.service.RegisterUserService;

@RestController
public class RegisterUserController {
	
	@Autowired 
	RegisterUserService registerUserService;
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public ResponseEntity<RegisterResponse> registerUser(@RequestBody User user)
	{
		RegisterResponse response;
		if (!registerUserService.checkIfUsernameAlreadyExists(user)) {
			if (registerUserService.registerUser(user) > 0) {
				response = new RegisterResponse();
				response.setResponseCode(1);
				response.setResponseMessage("Insert Success");
				response.setResponseStatus("OK");
				System.out.println("User already exist");
				return ResponseEntity.ok().body(response);
			} else {
				response = new RegisterResponse();
				response.setResponseCode(0);
				response.setResponseMessage("Error while inserting");
				response.setResponseStatus("OK");
				
				return ResponseEntity.ok().body(response);
			}
		} else {
			response = new RegisterResponse();
			response.setResponseCode(0);
			response.setResponseMessage("UserName already exists..");
			response.setResponseStatus("OK");
			return ResponseEntity.ok().body(response);
		}
	}

}
