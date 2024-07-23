package it.ashyzan.pizzeria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tabellaingredienti")
public class IngredientiModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@NotBlank(message = "Il nome ingrediente è obbligatorio")
	@Column(name = "nomeingredienti", nullable = false)
	private String nomeIngrediente;

	@ManyToMany(mappedBy = "listaIngredienti")
	private List<PizzaModel> pizze;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeIngrediente() {
		return nomeIngrediente;
	}

	public void setNomeIngrediente(String nomeIngrediente) {
		this.nomeIngrediente = nomeIngrediente;
	}

	public List<PizzaModel> getPizze() {
		return pizze;
	}

	public void setPizze(List<PizzaModel> pizze) {
		this.pizze = pizze;
	}

	// GETTER SETTER //

}
