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

	@GetMapping
	public String index(Model model) {

		List<IngredientiModel> ListaIng = ingredientiRepository.findAll();
		model.addAttribute("listaingredienti", ListaIng);

		return "/ingredienti/index";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("ingrediente") IngredientiModel listaIngredienti,
			BindingResult bindingresult, Model model) {

		if (bindingresult.hasErrors()) {
			return "/ingredienti/index";

		}

		ingredientiRepository.save(listaIngredienti);

		return "redirect: /ingredienti/index";
	}

}