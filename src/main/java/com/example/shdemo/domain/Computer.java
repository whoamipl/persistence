package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "computer.unsold", query = "Select c from Computer c where c.sold = false")
})
public class Computer {

	private Long id;
	private String model;
	private Boolean sold;
//	@ManyToOne
//rivate Person owner;
//	@ManyToOne
//rivate Producer producent;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public Computer(String producent, String model) {
		this.model = model;
		this.sold = false;
	}

	public Computer() {

	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

//	public Person getOwner() { return owner; }
//
//	public void setOwner(Person owner) { this.owner = owner; }

//	public Producer getProducent() { return producent; }
//
//	public void setProducent(Producer producent) { this.producent = producent; }
}
