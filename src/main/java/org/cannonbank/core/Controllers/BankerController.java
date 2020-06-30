package org.cannonbank.core.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.cannonbank.core.Entities.Banker;
import org.cannonbank.core.Repositories.BankerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")


public class BankerController {

	@Autowired
	BankerRepository bankerRepository ;

	
	
	@RequestMapping(value="/bankers")
	 public String login(Model model) {
		 
		List <Banker> bankers=bankerRepository.findAll();
		model.addAttribute("bankers",bankers);
		model.addAttribute("banker", new Banker()); 
		 
		return "bankers";
	 }
	@PostMapping("/addBanker")
	public String addBanker(@Valid Banker banker, BindingResult result, Model model) {
		if (result.hasErrors()) {			
			model.addAttribute("erreur", "values incorrects !");
			banker = new Banker();
        }
		else
		{
			try {
			bankerRepository.save(banker);
	        model.addAttribute("succes", "added successfuly !");
			}
			catch(Exception e)
			{
			model.addAttribute("erreur", "probleme exception :"+e.getMessage());
			banker = new Banker();	
			}
		}
		
		List <Banker> bankers=bankerRepository.findAll();
		model.addAttribute("banker", banker);
        model.addAttribute("bankers", bankers);
        return "bankers";
	}
	
	
	@PostMapping("/updateBanker")
	public String updateBanker(@Valid Banker banker, 
	  BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("updateErreur", "values incorrects !");
			banker = new Banker();
        }
		else
		{
			try {
				Banker test = bankerRepository.findById(banker.getId())
						.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:"));
						bankerRepository.save(banker);
				        model.addAttribute("updateSucces", "updated successfuly !");
			}
			catch(IllegalArgumentException e)
			{
				model.addAttribute("updateErreur", e.getMessage());
				banker = new Banker();
			}			
		}
		List <Banker> bankers=bankerRepository.findAll();
		model.addAttribute("banker", banker);
        model.addAttribute("bankers", bankers);
        return "bankers";
	}
	
	
	@GetMapping("/deleteBanker/{id}")
	public String deleteBanker(@PathVariable("id") long id, Model model) {
	  try {  
		 Banker banker = bankerRepository.findById(id)
	    .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    bankerRepository.delete(banker);
	    model.addAttribute("deleteSucces", "deleted successfuly!");
	    return "bankers";
	  }
	  catch(Exception e)
	  {
		  model.addAttribute("deleteErreur", e.getMessage());
	  }
	  finally {
		  List <Banker> bankers=bankerRepository.findAll();
	      model.addAttribute("bankers", bankers);
			model.addAttribute("banker", new Banker());

	  }

	   return "bankers";
	}




}
