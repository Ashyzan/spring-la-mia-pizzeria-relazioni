package it.ashyzan.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ashyzan.pizzeria.model.IngredientiModel;


public interface IngredientiRepository extends JpaRepository<IngredientiModel, Integer> {

    public IngredientiModel findBynomeIngredienteIgnoreCase(String nomeIngrediente);
}
