package it.ashyzan.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ashyzan.pizzeria.model.PizzaModel;

// dico a pring di agire in autonomia collegando il modello col db e creando quindi le tabelle
public interface PizzaRepository extends JpaRepository<PizzaModel, Integer> {

}
