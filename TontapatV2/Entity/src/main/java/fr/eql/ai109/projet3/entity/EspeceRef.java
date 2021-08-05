package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="espece_ref")
public class EspeceRef implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="espece_id", unique=true, nullable=false)
	private int especeId;

	@Column(name="espece", length=254)
	private String libelleEspece;

	
	@OneToMany(mappedBy="especeRef")
	private List<RaceRef> raceRefs;

//	@OneToMany(mappedBy="especeRef")
//	private List<Terrain> terrains;

	public int getEspeceId() {
		return especeId;
	}


	public void setEspeceId(int especeId) {
		this.especeId = especeId;
	}


	public String getLibelleEspece() {
		return libelleEspece;
	}


	public void setLibelleEspece(String libelleEspece) {
		this.libelleEspece = libelleEspece;
	}


	public List<RaceRef> getRaceRefs() {
		return raceRefs;
	}


	public void setRaceRefs(List<RaceRef> raceRefs) {
		this.raceRefs = raceRefs;
	}




	
	
}
