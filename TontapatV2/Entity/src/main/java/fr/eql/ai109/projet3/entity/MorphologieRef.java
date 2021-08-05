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
@Table(name="morphologie_ref")
public class MorphologieRef implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_morphologie", unique=true, nullable=false)
	private int idMorphologie;
	
	@Column(name="libelle_morphologie", length=254)
	private String libelleMorphologie;
	
	@ManyToMany(mappedBy="morphologieRefs")
	private List<RaceRef> raceRefs;
	
	@OneToMany(mappedBy="morphologieRef")
	private List<ProportionMorphologie> proportionMorphologies;

	public int getIdMorphologie() {
		return idMorphologie;
	}

	public void setIdMorphologie(int idMorphologie) {
		this.idMorphologie = idMorphologie;
	}

	public String getLibelleMorphologie() {
		return libelleMorphologie;
	}

	public void setLibelleMorphologie(String libelleMorphologie) {
		this.libelleMorphologie = libelleMorphologie;
	}

	public List<RaceRef> getRaceRefs() {
		return raceRefs;
	}

	public void setRaceRefs(List<RaceRef> raceRefs) {
		this.raceRefs = raceRefs;
	}

	public List<ProportionMorphologie> getProportionMorphologies() {
		return proportionMorphologies;
	}

	public void setProportionMorphologies(List<ProportionMorphologie> proportionMorphologies) {
		this.proportionMorphologies = proportionMorphologies;
	}

}
