package it.ashyzan.pizzeria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizza")
public class PizzaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	// not blank va solo sulle stringhe, permette di cancellare l'input
	// se l'utente inserisce spazi nel field e restituire null.
	@NotBlank(message = "Il nome della pizza è obbligatorio")
	@Column(name = "nome", nullable = false)
	private String nome;

	@NotBlank(message = "La descrizione della pizza è obbligatoria")
	@Column(name = "descrizione", nullable = false)
	private String descrizione;

	@Column(name = "ingredienti", nullable = true)
	private String ingredienti;

	@Column(name = "fotourl", nullable = true)
	private String fotourl;

	@NotNull(message = "Il prezzo della pizza è obbligatorio")
	@Column(name = "prezzo", nullable = false)
	private double prezzo;

	@OneToMany(mappedBy = "pizza")
	private List<OffertaSpecialeModel> offerte;

	@ManyToMany()
	@JoinTable(name = "pizza_listaIngredienti", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "listaIngredienti_id"))
	private List<IngredientiModel> listaIngredienti;

	// GETTER SETTER ///

	public List<IngredientiModel> getListaIngredienti() {
		return listaIngredienti;
	}

	public void setListaIngredienti(List<IngredientiModel> listaIngredienti) {
		this.listaIngredienti = listaIngredienti;
	}

	public List<OffertaSpecialeModel> getOfferte() {
		return offerte;
	}

	public void setOfferte(List<OffertaSpecialeModel> offerte) {
		this.offerte = offerte;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFotourl() {
		return fotourl;
	}

	public void setFotourl(String fotourl) {
		this.fotourl = fotourl;
	}

	public String getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(String ingredienti) {
		this.ingredienti = ingredienti;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

}
