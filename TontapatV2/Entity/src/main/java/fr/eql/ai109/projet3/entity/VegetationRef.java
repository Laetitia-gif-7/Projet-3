package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="vegetation_ref")
public class VegetationRef implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_vegetation", unique=true, nullable=false)
	private int idVegetation;
	
	@Column(name="libelle_vegetation", length=254)
	private String libelleVegetation;
	
	
	
	
	
	@ManyToMany(mappedBy="vegetationRefs")
	private List<RaceRef> raceRefs;
	
	
	
	
	
	
	@OneToMany(mappedBy="vegetationRef")
	private List<ProportionVegetation> proportionVegetations;
	
	public int getIdVegetation() {
		return idVegetation;
	}

	public void setIdVegetation(int idVegetation) {
		this.idVegetation = idVegetation;
	}

	public String getLibelleVegetation() {
		return libelleVegetation;
	}

	public void setLibelleVegetation(String libelleVegetation) {
		this.libelleVegetation = libelleVegetation;
	}

	public List<RaceRef> getRaceRefs() {
		return raceRefs;
	}

	public void setRaceRefs(List<RaceRef> raceRefs) {
		this.raceRefs = raceRefs;
	}

	public List<ProportionVegetation> getProportionVegetations() {
		return proportionVegetations;
	}

	public void setProportionVegetations(List<ProportionVegetation> proportionVegetations) {
		this.proportionVegetations = proportionVegetations;
	}

}
