package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProportionMorphologiePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="id_terrain", insertable=false, updatable=false, unique=true, nullable=false)
	private int idTerrain;

	@Column(name="id_morphologie", insertable=false, updatable=false, unique=true, nullable=false)
	private int idMorphologie;

	public ProportionMorphologiePK() {
	}
	public int getIdTerrain() {
		return this.idTerrain;
	}
	public void setIdTerrain(int idTerrain) {
		this.idTerrain = idTerrain;
	}
	public int getIdMorphologie() {
		return this.idMorphologie;
	}
	public void setIdMorphologie(int idMorphologie) {
		this.idMorphologie = idMorphologie;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProportionMorphologiePK)) {
			return false;
		}
		ProportionMorphologiePK castOther = (ProportionMorphologiePK)other;
		return 
			(this.idTerrain == castOther.idTerrain)
			&& (this.idMorphologie == castOther.idMorphologie);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idTerrain;
		hash = hash * prime + this.idMorphologie;
		
		return hash;
	}
}