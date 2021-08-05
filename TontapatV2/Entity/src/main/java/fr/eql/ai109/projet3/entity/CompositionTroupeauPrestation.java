package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="composition_troupeau_prestation")
public class CompositionTroupeauPrestation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_compo_troupo_presta") //, unique=true, nullable=false)
	private int idCompoTroupoPresta;
	
	// only attribute of the entity
	@Column(name="nb_animaux")
	private int nbAnimaux;
	
	////// Foreign key //////
	
	// default eager it is a toOne relationship
	@ManyToOne
	@JoinColumn(name="id_prestation", nullable=false)
	private Prestation prestation;
	
	// Uni-directional OneToOne should be enough, only need to get race libelle
	// Eager by default
	@OneToOne
	@JoinColumn(name="id_race")
	private RaceRef raceRef;
	
	@ManyToOne
	@JoinColumn(name="id_troupeau", nullable=false)
	private Troupeau troupeau;

	////////////////////////////////////////////////////////
	public int getIdCompoTroupoPresta() {
		return idCompoTroupoPresta;
	}

	public void setIdCompoTroupoPresta(int idCompoTroupoPresta) {
		this.idCompoTroupoPresta = idCompoTroupoPresta;
	}

	public int getNbAnimaux() {
		return nbAnimaux;
	}

	public void setNbAnimaux(int nbAnimaux) {
		this.nbAnimaux = nbAnimaux;
	}

	public Prestation getPrestation() {
		return prestation;
	}

	public void setPrestation(Prestation prestation) {
		this.prestation = prestation;
	}

	public RaceRef getRaceRef() {
		return raceRef;
	}

	public void setRaceRef(RaceRef raceRef) {
		this.raceRef = raceRef;
	}

	public Troupeau getTroupeau() {
		return troupeau;
	}

	public void setTroupeau(Troupeau troupeau) {
		this.troupeau = troupeau;
	}
	
	
}
