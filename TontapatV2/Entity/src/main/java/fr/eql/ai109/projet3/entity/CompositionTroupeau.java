package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="composition_troupeau")
public class CompositionTroupeau implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CompositionTroupeauPK id;
	
	@Column(name="nb_animaux")
	private int nbAnimaux;


	@ManyToOne
	@JoinColumn(name="id_race", nullable=false, insertable=false, updatable=false)
	private RaceRef raceRef;


	@ManyToOne
	@JoinColumn(name="id_troupeau", nullable=false, insertable=false, updatable=false)
	private Troupeau troupeau;


	public CompositionTroupeauPK getId() {
		return id;
	}


	public void setId(CompositionTroupeauPK id) {
		this.id = id;
	}


	public int getNbAnimaux() {
		return nbAnimaux;
	}


	public void setNbAniamux(int nbAnimaux) {
		this.nbAnimaux = nbAnimaux;
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
