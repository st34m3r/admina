package org.cannonbank.core.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MainController {

	@RequestMapping(value="/login")
	 public String login() {
		 
		 return "login";
	 }


	@RequestMapping(value="/admin")
	 public String home() {
		 
		 return "dashboard";
	 }
	@RequestMapping(value="/")
	 public String dashboard() {
		 
		 return "login";
	 }

	@RequestMapping(value="/403")
	 public String accessDenied() {
		 
		 return "403";
	 }
}
