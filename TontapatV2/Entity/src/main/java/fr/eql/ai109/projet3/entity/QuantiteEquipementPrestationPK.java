package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class QuantiteEquipementPrestationPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="id_equipement", insertable=false, updatable=false, unique=true, nullable=false)
	private int idEquipement;

	@Column(name="id_prestation", insertable=false, updatable=false, unique=true, nullable=false)
	private int idPrestation;

	public QuantiteEquipementPrestationPK() {
	}
	public int getIdEquipement() {
		return this.idEquipement;
	}
	public void setIdEquipement(int idEquipement) {
		this.idEquipement = idEquipement;
	}
	public int getIdPrestation() {
		return this.idPrestation;
	}
	public void setIdPrestation(int idPrestation) {
		this.idPrestation = idPrestation;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof QuantiteEquipementPrestationPK)) {
			return false;
		}
		QuantiteEquipementPrestationPK castOther = (QuantiteEquipementPrestationPK)other;
		return 
			(this.idEquipement == castOther.idEquipement)
			&& (this.idPrestation == castOther.idPrestation);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEquipement;
		hash = hash * prime + this.idPrestation;
		
		return hash;
	}
}