package it.ashyzan.pizzeria.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.ashyzan.pizzeria.model.OffertaSpecialeModel;
import it.ashyzan.pizzeria.model.PizzaModel;
import it.ashyzan.pizzeria.repository.OffersRepository;
import it.ashyzan.pizzeria.repository.PizzaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OfferteController {

	@Autowired
	private OffersRepository offertarepository;
	@Autowired
	private PizzaRepository pizzarepository;

	// inserisco offerte che era su pizzacotroller
	// OFFERTE SPECIALI

	@GetMapping("/create/{id}")
	public String Offerta(@PathVariable("id") Integer id, Model model) {

		PizzaModel pizza = pizzarepository.findById(id).get();
		OffertaSpecialeModel offerta = new OffertaSpecialeModel();
		offerta.setDataInizioOfferta(LocalDate.now());
		offerta.setDataFineOfferta(LocalDate.now());
		offerta.setPizza(pizza);

		model.addAttribute("offerta", offerta);
		// model.addAttribute("standardDate", new Date());
		model.addAttribute("editMode", false);

		return "offerte/editoffers";
	}
	// fine inserimento

//	@GetMapping("/create")
//	public String create(Model model) {
//		model.addAttribute("offerta", new OffertaSpecialeModel());
//		return "/offerte/editoffers";
//	}

	@PostMapping("/create")
	public String store(@ModelAttribute("offerta") OffertaSpecialeModel nuovaofferta, Model model,
			BindingResult bindingresult) {

		if (bindingresult.hasErrors()) {

			return "offerte/editoffers";
		}

		offertarepository.save(nuovaofferta);

		return "redirect:/pizzeria/ingredienti/" + nuovaofferta.getPizza().getId();
	}

	// modifica delle offerte già inserite

	@GetMapping("/edit/{id}")
	public String modificaOfferta(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("offerta", offertarepository.getReferenceById(id));
//		OffertaSpecialeModel offerta = new OffertaSpecialeModel();
//		offerta.getDataInizioOfferta();
//		offerta.getDataFineOfferta();
		return "/offerte/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("offers") OffertaSpecialeModel offerta, BindingResult bindingresult,
			Model model) {

		offertarepository.save(offerta);

		// System.out.println("ho salvato offerta " + offerta);

		return "redirect:/pizzeria/ingredienti/" + offerta.getPizza().getId();
	}

	// delete offerta speciale
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		offertarepository.deleteById(id);
		// System.out.println("ho cancellato offerta con id " + id);

		return "redirect:/pizzeria/index";
	}
}
