package com.nathanpulliam.controllerviews.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nathanpulliam.controllerviews.models.User;
import com.nathanpulliam.controllerviews.services.UserService;

//imports removed for brevity
@Controller
public class UserController {
	private final UserService userService;
 
	public UserController(UserService userService) {
	     this.userService = userService;
	 }
	 
	 @RequestMapping("/registration")
	 public String registerForm(@ModelAttribute("user") User user) {
	 return "registrationPage.jsp";
	 }
	 @RequestMapping("/login")
	 public String login() {
	     return "loginPage.jsp";
	 }
	 
	 @RequestMapping(value="/registration", method=RequestMethod.POST)
	 public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult results, HttpSession session) {
		 // if result has errors, return the registration page (don't worry about validations just now)
		 if(results.hasErrors()) {
			 return "registrationPage.jsp";
		 } else {
			 // else, save the user in the database, save the user id in session, and redirect them to the /home route
			 User newUser = userService.registerUser(user);
			 session.setAttribute("user_id", newUser.getId());
			 return "redirect:/home";
		 }
	 }
	 
	 @RequestMapping(value="/login", method=RequestMethod.POST)
	 public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		 // if the user is authenticated, save their user id in session
		 if(userService.authenticateUser(email, password)) {
			 User user = userService.findByEmail(email);
			 session.setAttribute("user_id", user.getId());
			 return "redirect:/home";
		 } else {
			 // else, add error messages and return the login page
			 redirectAttributes.addFlashAttribute("error", "INVALID CREDENTIALS");
			 return "redirect:/login";
		 }
		 
	 }
	 
	 @RequestMapping("/home")
	 public String home(HttpSession session, Model model) {
	     // get user from session, save them in the model and return the home page
		 Long loggedId = (Long) session.getAttribute("user_id");
		 User loggedUser = userService.findUserById(loggedId);
		 model.addAttribute("loggedUser", loggedUser);
		 return "homePage.jsp";
		 
	 }
	 @RequestMapping("/logout")
	 public String logout(HttpSession session) {
	     // invalidate session
		 session.invalidate();
		 // redirect to login page
		 return "redirect:/login";
	 }
}
