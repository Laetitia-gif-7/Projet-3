package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="quantite_equipement")
public class QuantiteEquipement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private QuantiteEquipementPK id;
	
	private int quantite;
	
	@ManyToOne
	@JoinColumn(name="id_equipement", nullable=false, insertable=false, updatable=false)
	private Equipement equipement;
	
	@ManyToOne
	@JoinColumn(name="id_terrain", nullable=false, insertable=false, updatable=false)
	private Terrain terrain;

	public Equipement getEquipement() {
		return equipement;
	}

	public void setEquipement(Equipement equipement) {
		this.equipement = equipement;
	}

	public QuantiteEquipementPK getId() {
		return id;
	}

	public void setId(QuantiteEquipementPK id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Equipement getEquipment() {
		return equipement;
	}

	public void setEquipment(Equipement equipement) {
		this.equipement = equipement;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

}
