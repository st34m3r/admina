package org.cannonbank.core.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.cannonbank.core.Entities.Currency;
import org.cannonbank.core.Repositories.CurrencyRepository;
import org.cannonbank.core.Repositories.CurrencyRepository;
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
public class CurrencyController {

	@Autowired
	CurrencyRepository currencyRepository ;

	
	
	@RequestMapping(value="/currencies")
	 public String login(Model model) {
		 
		List <Currency> currencies=currencyRepository.findAll();
		model.addAttribute("currencies",currencies);
		model.addAttribute("currency", new Currency()); 
		 
		return "currencies";
	 }
	@PostMapping("/addCurrency")
	public String addCurrency(@Valid Currency currency, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreur", "values incorrects !");
			currency = new Currency();
        }
		else
		{
			currencyRepository.save(currency);
	        model.addAttribute("succes", "added successfuly !");

		}
		
		List <Currency> currencies=currencyRepository.findAll();
	 	model.addAttribute("currency", currency);
        model.addAttribute("currencies", currencies);
        return "currencies";
	}
	
	
	@PostMapping("/updateCurrency")
	public String updateCurrency(@Valid Currency currency, 
	  BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("updateErreur", "values incorrects !");
			currency = new Currency();
        }
		else
		{
			try {
				Currency test = currencyRepository.findById(currency.getId())
						.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:"));
						currencyRepository.save(currency);
				        model.addAttribute("updateSucces", "updated successfuly !");
				        
			}
			catch(IllegalArgumentException e)
			{
				model.addAttribute("updateErreur", e.getMessage());
				currency = new Currency();
			}			
		}
		List <Currency> currencies=currencyRepository.findAll();
		model.addAttribute("currency", currency);
        model.addAttribute("currencies", currencies);
        return "currencies";
	}
	
	
	@GetMapping("/deleteCurrency/{id}")
	public String deleteCurrency(@PathVariable("id") int id, Model model) {
	  try {  
		 Currency currency = currencyRepository.findById(id)
	    .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    currencyRepository.delete(currency);
	    model.addAttribute("deleteSucces", "deleted successfuly!");
	    return "currencies";
	  }
	  catch(Exception e)
	  {
		  model.addAttribute("deleteErreur", e.getMessage());
	  }
	  finally {
		  List <Currency> currencies=currencyRepository.findAll();
	      model.addAttribute("currencies", currencies);
	      model.addAttribute("currency", new Currency());

	  }

	   return "currencies";
	}




}
