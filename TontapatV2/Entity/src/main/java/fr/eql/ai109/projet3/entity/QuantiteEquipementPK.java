package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class QuantiteEquipementPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="id_terrain", insertable=false, updatable=false, unique=true, nullable=false)
	private int idTerrain;

	@Column(name="id_equipement", insertable=false, updatable=false, unique=true, nullable=false)
	private int idEquipement;

	public QuantiteEquipementPK() {
	}
	public int getIdTerrain() {
		return this.idTerrain;
	}
	public void setIdTerrain(int idTerrain) {
		this.idTerrain = idTerrain;
	}
	public int getIdEquipement() {
		return this.idEquipement;
	}
	public void setIdEquipement(int idEquipement) {
		this.idEquipement = idEquipement;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof QuantiteEquipementPK)) {
			return false;
		}
		QuantiteEquipementPK castOther = (QuantiteEquipementPK)other;
		return 
			(this.idTerrain == castOther.idTerrain)
			&& (this.idEquipement == castOther.idEquipement);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idTerrain;
		hash = hash * prime + this.idEquipement;
		
		return hash;
	}

}
