package com.SpringSignUp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SpringSignUp.Model.User;
import com.SpringSignUp.Service.UserService;

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("user/login");
		return model;
	}
	
	@RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user",user);
		model.setViewName("user/signup");
		
		return model;
	}
	
	//valid to validated 
	@RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
	public ModelAndView createUser(@Validated User user,BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getMail());
		
		if(userExists != null) {
			bindingResult.rejectValue("email", "error.user","This email already exists!");
		}
		if(bindingResult.hasErrors()) {
			model.setViewName("user/signup");
		} else {
			userService.saveUser(user);
			model.addObject("msg","User has been registered successfully");
			model.addObject("user",new User());
			model.setViewName("user/signup");
		}
		
		return model;
	}
	
	@RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
	public ModelAndView home() {
		
	}
}