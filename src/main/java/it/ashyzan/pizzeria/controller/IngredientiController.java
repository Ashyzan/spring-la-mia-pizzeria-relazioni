package it.ashyzan.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.ashyzan.pizzeria.model.IngredientiModel;
import it.ashyzan.pizzeria.repository.IngredientiRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

	@Autowired
	private IngredientiRepository ingredientiRepository;

	@GetMapping("/create")
	public String index(Model model) {

		List<IngredientiModel> ListaIng = ingredientiRepository.findAll();
		model.addAttribute("listaingredienti", ListaIng);
		model.addAttribute("nuovoingrediente", new IngredientiModel());

		return "ingredienti/index";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("nuovoingrediente") 
	IngredientiModel formIngredienti, BindingResult bindingresult, Model model) {

		if (bindingresult.hasErrors()) {
		    
		    List<IngredientiModel> ListaIng = ingredientiRepository.findAll();
			model.addAttribute("listaingredienti", ListaIng);
			model.addAttribute("nuovoingrediente", formIngredienti);
			
			return "ingredienti/index";

		}

		ingredientiRepository.save(formIngredienti);

		return "redirect:create";
	}

}