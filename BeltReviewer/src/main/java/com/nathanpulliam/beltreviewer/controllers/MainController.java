package com.nathanpulliam.beltreviewer.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nathanpulliam.beltreviewer.models.Event;
import com.nathanpulliam.beltreviewer.models.User;
import com.nathanpulliam.beltreviewer.services.EventService;
import com.nathanpulliam.beltreviewer.services.UserService;
import com.nathanpulliam.beltreviewer.validators.UserValidator;

@Controller
public class MainController {
	
	@Autowired
	private UserService userServ;
	@Autowired
	private EventService eventServ;
	
	@Autowired
	private UserValidator userValid;
	
	String[] states = {"AL", "AK", "AR", "AZ", "CA", "CO", "CT", "DE", "DC", 
			"FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", 
			"MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", 
			"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
			"SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
	
	@GetMapping("")
	public String index(@ModelAttribute("userObj")User emptyUser, Model model) {
		model.addAttribute("states", states);
		return "index.jsp";
	}
	
	@PostMapping("/registration")
	public String register(
			@Valid @ModelAttribute("userObj") User filledUser, BindingResult results,
			HttpSession session,
			Model model
	) {
		// RUN OUR CUSTOM VALIDATIONS
		userValid.validate(filledUser,results );
		// CHECK TO SEE IF THERE ARE VALIDATIONS ERRORS
		if(results.hasErrors()) {
			
			model.addAttribute("states", states);
			return "index.jsp";
		}
		else {
			// CREATE USER
			User newUser = userServ.registerUser(filledUser);
			session.setAttribute("user_id", newUser.getId());
			// RETURN REDIRECT TO HOME
			return "redirect:/events";
		}
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,@RequestParam("password") String password,
			HttpSession session, RedirectAttributes redirectAttributes
	) {
		// CHECK TO SEE IF EMAIL AND PASSWORD MATCH
		if(userServ.authenticateUser(email, password)) {
			User user = userServ.findByEmail(email);
			session.setAttribute("user_id", user.getId());
			 return "redirect:/events";
		}
		else {
			redirectAttributes.addFlashAttribute("error", "INVALID CREDENTIALS");
			return "redirect:/";
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// invalidate session
		session.invalidate();
		// redirect to login page
		return "redirect:/";
	}
	
	 @RequestMapping("/events")
	 public String home(
			 @ModelAttribute("eventObj") Event emptyEvent,
			 HttpSession session, Model model) {
	     // get user from session, save them in the model and return the home page
		 Long loggedId = (Long) session.getAttribute("user_id");
		 if(loggedId == null) {
			 return "redirect:/";
		 }
		 User loggedUser = userServ.findUserById(loggedId);
		 List<Event> eventsFromDb = eventServ.getEvents();
		 model.addAttribute("states", states);
		 model.addAttribute("loggedUser", loggedUser);
		 model.addAttribute("allEvents", eventsFromDb);
		 return "homePage.jsp";
	 }
	 
	 @PostMapping("/events/create")
	 public String createEvent(
			 @Valid @ModelAttribute("eventObj") Event filledEvent, BindingResult results,
			 Model model, HttpSession session) {
		 Long loggedId = (Long) session.getAttribute("user_id");
		 if(loggedId == null) {
			 return "redirect:/";
		 }
		 User loggedUser = userServ.findUserById(loggedId);
		 if(results.hasErrors()) {
			 List<Event> eventsFromDb = eventServ.getEvents();
			 model.addAttribute("states", states);
			 model.addAttribute("loggedUser", loggedUser);
			 model.addAttribute("allEvents", eventsFromDb);
			 return "homePage.jsp";
		 } else {
			 filledEvent.setHost(loggedUser);
			 eventServ.saveEvent(filledEvent);
			 return "redirect:/events";
		 }
	 }
	 
	 @GetMapping("/events/{eventId}/join")
	 public String joinEvent(
			 @PathVariable("eventId") Long eventId,
			 HttpSession session
			 ) {
		 Long loggedId = (Long) session.getAttribute("user_id");
		 if(loggedId == null) {
			 return "redirect:/";
		 }
		 User loggedUser = userServ.findUserById(loggedId);
		 Event eventToJoin = eventServ.getEventById(eventId);
		 eventToJoin.getJoinedUsers().add(loggedUser);
		 eventServ.saveEvent(eventToJoin);
		 return "redirect:/events";
	 }
	 @GetMapping("/events/{eventId}/canceljoin")
	 public String leaveEvent(
			 @PathVariable("eventId") Long eventId,
			 HttpSession session
			 ) {
		 Long loggedId = (Long) session.getAttribute("user_id");
		 if(loggedId == null) {
			 return "redirect:/";
		 }
		 User loggedUser = userServ.findUserById(loggedId);
		 Event eventToCancel = eventServ.getEventById(eventId);
		 eventToCancel.getJoinedUsers().remove(loggedUser);
		 eventServ.saveEvent(eventToCancel);
		 return "redirect:/events";
	 }
	 @GetMapping("events/{eventId}/edit")
	 public String editEvent(
			 @PathVariable("eventId") Long eventId,
			 Model model,
			 HttpSession session
			 ) {
		 Event eventToEdit = eventServ.getEventById(eventId);
		 model.addAttribute("states", states);
		 model.addAttribute("eventToEdit", eventToEdit);

		 if(session.getAttribute("user_id").equals(eventToEdit.getHost().getId())) {
			 return "edit_event.jsp";
		 } else {
			 return "404.jsp";
		 } 
	 }
	 @PutMapping("events/{eventId}/edit")
	 public String updateEvent(
			 @PathVariable("eventId") Long eventId,
			 @Valid @ModelAttribute("eventToEdit") Event eventToEdit, BindingResult results,
			 Model model,
			 HttpSession session
			 ) {
		 
		 if(results.hasErrors()) {
			 model.addAttribute("states", states);
			 return "edit_event.jsp";
		 } else {
			 
			 User loggedUser = userServ.findUserById( (Long)session.getAttribute("user_id") );
			 eventToEdit.setHost(loggedUser);
			 eventToEdit.setId(eventId);
			 eventServ.saveEvent(eventToEdit);
			 return "redirect:/events";
		 }
	 }
	 @GetMapping("events/{eventId}/delete")
	 public String deleteEvent(@PathVariable("eventId") Long eventId, HttpSession session) {
		 Event deleteEvent = eventServ.getEventById(eventId);
		 User loggedUser = userServ.findUserById((Long) session.getAttribute("user_id"));
		 if(deleteEvent.getHost().getId() == loggedUser.getId()) {
			 loggedUser.getEventsHosted().remove(deleteEvent);
			 eventServ.deleteEvent(eventId);
		 }
		 return "redirect:/events";
	 }
	 
}