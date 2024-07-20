package it.ashyzan.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.ashyzan.pizzeria.model.OffertaSpecialeModel;
import it.ashyzan.pizzeria.repository.OffersRepository;

@Controller
@RequestMapping("/offerte")
public class OfferteController {

	@Autowired
	private OffersRepository offertarepository;

	@PostMapping("/create")
	public String store(@ModelAttribute("offerta") OffertaSpecialeModel nuovaofferta, Model model,
			BindingResult bindingresult) {

		if (bindingresult.hasErrors()) {

			return "offerte/editoffers";
		}

		offertarepository.save(nuovaofferta);

		return "redirect:/pizzeria/ingredienti/" + nuovaofferta.getPizza().getId();
	}
}
