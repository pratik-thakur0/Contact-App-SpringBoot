package app.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.contact.dao.UserDAO;
import app.contact.domain.User;
import app.contact.services.UserService;

@RestController
public class UnitTestController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/test/user/save")
	public String testUserDAOSave(){
		User u = new User();
		u.setName("Pratik");
		u.setPhone("644456548");
		u.setEmail("pratik@gmail.com");
		u.setAddress("Indore");
		u.setLoginId("pratik");
		u.setPassword("pratik"); //TODO: should be encrypted
		userDAO.save(u);
		return "Save Success";
	}
	
	@GetMapping("/test/user/reg")
	public String testUserReg(){
		User u = new User();
		u.setName("Pratik");
		u.setPhone("644456548");
		u.setEmail("pratik@gmail.com");
		u.setAddress("Indore");
		u.setLoginId("p");
		u.setPassword("pratik"); //TODO: should be encrypted
		userService.register(u);
		return "Registration Success";
	}
	
}
