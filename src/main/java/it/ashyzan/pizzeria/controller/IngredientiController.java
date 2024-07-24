package it.ashyzan.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	    
	    // metodo per filtrare gli ingredienti e verificare che un ingrediente sia o meno già a db
	    // se ne può fare un metodo da richiamare a piacimento
	    if(formIngredienti.getNomeIngrediente() != null) {
		IngredientiModel ingredienteFiltrato = 
		ingredientiRepository.findBynomeIngredienteIgnoreCase(formIngredienti.getNomeIngrediente());
		if(ingredienteFiltrato != null) {
			bindingresult.addError(new ObjectError("Errore di inserimento", 
		"L'ingrediente esiste già"));
		}
	}
	    
		if (bindingresult.hasErrors()) {
		    // questi vanno rimessi perchè altrimenti il refresh è troppo forte e non filtra 
		    // (ripresi dal @GetMapping("/crate") )
		    	List<IngredientiModel> ListaIng = ingredientiRepository.findAll();
			model.addAttribute("listaingredienti", ListaIng);
			model.addAttribute("nuovoingrediente", formIngredienti);
			
			return "ingredienti/index";

		}

		ingredientiRepository.save(formIngredienti);

		return "redirect:create";
	}
	
	// delete ingrediente
		@PostMapping("/delete/{id}")
		public String delete(@PathVariable("id") Integer id) {

		    ingredientiRepository.deleteById(id);

			return "redirect:/ingredienti/create";
		}

}