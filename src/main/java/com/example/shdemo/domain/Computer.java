package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "computer.unsold", query = "Select c from Computer c where c.sold = false"),
		@NamedQuery(name = "computer.lastAdded", query = "select c.id from Computer c order by c.id desc")
})
public class Computer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String model;
	private Boolean sold;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)

	private Producer producer;

	public Computer() {

	}
	public Computer(String producent, String model) {
		this.model = model;
		this.sold = false;
	}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getModel() { return model; }

	public void setModel(String model) { this.model = model; }

	public Boolean getSold() { return sold; }

	public void setSold(Boolean sold) { this.sold = sold; }
	public Producer getProducer() { return producer; }

	public void setProducer(Producer producer) { this.producer = producer; }
}
