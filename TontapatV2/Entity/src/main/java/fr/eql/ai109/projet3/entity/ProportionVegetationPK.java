package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProportionVegetationPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="id_vegetation", insertable=false, updatable=false, unique=true, nullable=false)
	private int idVegetation;

	@Column(name="id_terrain", insertable=false, updatable=false, unique=true, nullable=false)
	private int idTerrain;

	public ProportionVegetationPK() {
	}
	public int getIdVegetation() {
		return this.idVegetation;
	}
	public void setIdVegetation(int idVegetation) {
		this.idVegetation = idVegetation;
	}
	public int getIdTerrain() {
		return this.idTerrain;
	}
	public void setIdTerrain(int idTerrain) {
		this.idTerrain = idTerrain;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProportionVegetationPK)) {
			return false;
		}
		ProportionVegetationPK castOther = (ProportionVegetationPK)other;
		return 
			(this.idVegetation == castOther.idVegetation)
			&& (this.idTerrain == castOther.idTerrain);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idVegetation;
		hash = hash * prime + this.idTerrain;
		
		return hash;
	}
}