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

import it.ashyzan.pizzeria.model.OffertaSpecialeModel;
import it.ashyzan.pizzeria.model.PizzaModel;
import it.ashyzan.pizzeria.repository.OffersRepository;
import it.ashyzan.pizzeria.repository.PizzaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzeria")
public class PizzaController {

	// l'annotazione autowired serve a spring per dirgli di gestire autonomamente
	// tramite la dependency injection e inversion of control
	// inietta nel repository la new a runtime
	@Autowired
	private PizzaRepository pizzarepository;

	@Autowired
	private OffersRepository offertarepository;

	@GetMapping("index")
	public String index(Model model) {
		List<PizzaModel> pizze = pizzarepository.findAll();
		model.addAttribute("pizze", pizze);

		return "/pizzeria/index";
	}

	@GetMapping("/ingredienti/{id}")
	public String dettaglioPizza(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("ingredienti", pizzarepository.getReferenceById(id));
		model.addAttribute("pizza", pizzarepository.getReferenceById(id));

		List<OffertaSpecialeModel> offerte = offertarepository.findAll();
		model.addAttribute("offerte", offerte);

		return "/pizzeria/ingredienti";
	}

	// inserimento nuova pizza

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("pizza", new PizzaModel());
		return "/pizzeria/create";
	}

	@PostMapping("/create")
	public String salvaPizza(@Valid @ModelAttribute("pizza") PizzaModel pizza, BindingResult bindingresult, Model model)

	{

		// CONTROLLO PREZZO

		if (pizza.getPrezzo() <= 0) {

			bindingresult.addError(new ObjectError("Errore di prezzo", "Il prezzo della pizza è obbligatorio"));

		}

		if (bindingresult.hasErrors()) {
			return "/pizzeria/create";
		}
		pizzarepository.save(pizza);

		return "redirect:/pizzeria/index";
	}

	// modifica delle pizze già inserite

	@GetMapping("/edit/{id}")
	public String modificaPizza(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("pizza", pizzarepository.getReferenceById(id));
		return "/pizzeria/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") PizzaModel pizza, BindingResult bindingresult, Model model) {

		if (pizza.getPrezzo() <= 0) {

			bindingresult.addError(new ObjectError("Errore di prezzo", "Il prezzo della pizza è obbligatorio"));

		}

		if (bindingresult.hasErrors()) {
			return "/pizzeria/edit";
		}
		pizzarepository.save(pizza);

		return "redirect:/pizzeria/index";
	}

	// delete pizza
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		pizzarepository.deleteById(id);

		return "redirect:/pizzeria/index";
	}

//	// OFFERTE SPECIALI
//
//	@GetMapping("{id}/offerte")
//	public String Offerta(@PathVariable("id") Integer id, Model model) {
//
//		PizzaModel pizza = pizzarepository.findById(id).get();
//		OffertaSpecialeModel offerta = new OffertaSpecialeModel();
//		offerta.setDataInizioOfferta(LocalDate.now());
//		offerta.setDataFineOfferta(LocalDate.now());
//		offerta.setPizza(pizza);
//
//		model.addAttribute("offerta", offerta);
//		model.addAttribute("editMode", false);
//
//		return "offerte/editoffers";
//	}

}
