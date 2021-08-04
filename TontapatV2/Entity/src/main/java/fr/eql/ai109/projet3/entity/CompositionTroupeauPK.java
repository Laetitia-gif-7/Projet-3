package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositionTroupeauPK implements Serializable {


	private static final long serialVersionUID = 1L;

	@Column(name="id_troupeau", insertable=false, updatable=false, unique=true, nullable=false)
	private int idTroupeau;

	@Column(name="id_race", insertable=false, updatable=false, unique=true, nullable=false)
	private int idRace;

	public CompositionTroupeauPK() {
	}

	public int getIdTroupeau() {
		return idTroupeau;
	}

	public void setIdTroupeau(int idTroupeau) {
		this.idTroupeau = idTroupeau;
	}

	public int getIdRace() {
		return idRace;
	}

	public void setIdRace(int idRace) {
		this.idRace = idRace;
	}

	

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CompositionTroupeauPK)) {
			return false;
		}
		CompositionTroupeauPK castOther = (CompositionTroupeauPK)other;
		return 
			(this.idTroupeau == castOther.idTroupeau)
			&& (this.idRace == castOther.idRace);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idTroupeau;
		hash = hash * prime + this.idRace;
		
		return hash;
	}
	
	
	
}
