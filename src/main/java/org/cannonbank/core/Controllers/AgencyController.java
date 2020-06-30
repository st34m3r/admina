package org.cannonbank.core.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.cannonbank.core.Entities.Agency;
import org.cannonbank.core.Repositories.AgencyRepository;
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
public class AgencyController {

	@Autowired
	AgencyRepository agencyRepository ;

	
	
	@RequestMapping(value="/agencies")
	 public String login(Model model) {
		 
		List <Agency> agencies=agencyRepository.findAll();
		model.addAttribute("agencies",agencies);
		model.addAttribute("agency", new Agency()); 
		 
		return "agencies";
	 }
	@PostMapping("/addAgency")
	public String addAgency(@Valid Agency agency, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreur", "values incorrects !");
			agency = new Agency();
        }
		else
		{
			agencyRepository.save(agency);
	        model.addAttribute("succes", "added successfuly !");

		}
		
		List <Agency> agencies=agencyRepository.findAll();
		model.addAttribute("agency", agency);
        model.addAttribute("agencies", agencies);
        return "agencies";
	}
	
	
	@PostMapping("/updateAgency")
	public String updateAgency(@Valid Agency agency, 
	  BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("updateErreur", "values incorrects !");
			agency = new Agency();
        }
		else
		{
			try {
				Agency test = agencyRepository.findById(agency.getAgencyId())
						.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:"));
						agencyRepository.save(agency);
				        model.addAttribute("updateSucces", "updated successfuly !");
			}
			catch(IllegalArgumentException e)
			{
				model.addAttribute("updateErreur", e.getMessage());
				agency = new Agency();
			}			
		}
		List <Agency> agencies=agencyRepository.findAll();
		model.addAttribute("agency", agency);
        model.addAttribute("agencies", agencies);
        return "agencies";
	}
	
	
	@GetMapping("/deleteAgency/{id}")
	public String deleteAgency(@PathVariable("id") int id, Model model) {
	  try {  
		 Agency agency = agencyRepository.findById(id)
	    .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    agencyRepository.delete(agency);
	    model.addAttribute("deleteSucces", "deleted successfuly!");
	    return "agencies";
	  }
	  catch(Exception e)
	  {
		  model.addAttribute("deleteErreur", e.getMessage());
	  }
	  finally {
		  List <Agency> agencies=agencyRepository.findAll();
	      model.addAttribute("agencies", agencies);
			model.addAttribute("agency", new Agency());

	  }

	   return "agencies";
	}




}
