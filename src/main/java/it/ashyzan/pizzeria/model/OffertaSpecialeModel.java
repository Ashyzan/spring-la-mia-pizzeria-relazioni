package it.ashyzan.pizzeria.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "offers")
public class OffertaSpecialeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "titolo_offerta", nullable = false)
	private String titoloOfferta;

	@Column(name = "data_onizio_offerta", nullable = false)
	private LocalDate dataInizioOfferta;

	@Column(name = "data_fine_offerta", nullable = true)
	private LocalDate dataFineOfferta;

	@ManyToOne
	@JoinColumn(name = "pizza_id", nullable = false)
	private PizzaModel pizza;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitoloOfferta() {
		return titoloOfferta;
	}

	public void setTitoloOfferta(String titoloOfferta) {
		this.titoloOfferta = titoloOfferta;
	}

	public LocalDate getDataInizioOfferta() {
		return dataInizioOfferta;
	}

	public void setDataInizioOfferta(LocalDate dataInizioOfferta) {
		this.dataInizioOfferta = dataInizioOfferta;
	}

	public LocalDate getDataFineOfferta() {
		return dataFineOfferta;
	}

	public void setDataFineOfferta(LocalDate dataFineOfferta) {
		this.dataFineOfferta = dataFineOfferta;
	}

	public PizzaModel getPizza() {
		return pizza;
	}

	public void setPizza(PizzaModel pizza) {
		this.pizza = pizza;
	}

}
