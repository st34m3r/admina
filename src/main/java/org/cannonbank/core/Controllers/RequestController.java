package org.cannonbank.core.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.cannonbank.core.Entities.Currency;
import org.cannonbank.core.Entities.Request;
import org.cannonbank.core.Repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping(value="/admin")
@Controller
public class RequestController {

	@Autowired
	RequestRepository requestRepository ;

	
	
	@RequestMapping(value="/requests")
	 public String statuts(Model model) {
		List <Request> requests=requestRepository.findAll();
		model.addAttribute("requests",requests);
		model.addAttribute("request", new Request()); 
		 
		return "requests";
	 }
	
	@GetMapping("/requests/{isopen}")
	 public String getAuthorized(@PathVariable("isopen") boolean isopen, Model model) {
		List <Request> requests=requestRepository.findByOpen(isopen);
		model.addAttribute("requests",requests);
		model.addAttribute("request", new Request()); 
		 
		return "requests";
	 }

	
	@GetMapping("/requests/enableDisableRequest/{open}/{id}")
	 public String enableDisableRequest(@PathVariable("open") boolean open,@PathVariable("id") Integer id, Model model) {
		
		try {
			Request request = requestRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("Invalid  Id:"));
		request.setOpen(!open);
		requestRepository.save(request);
		List <Request> requests=requestRepository.findByOpen(open);
		model.addAttribute("requests",requests);
		}
		catch(Exception e)
		{
			List <Request> requests=requestRepository.findByOpen(open);
			model.addAttribute("requests",requests);	
		}
		
		return "requests";
	 }

}
