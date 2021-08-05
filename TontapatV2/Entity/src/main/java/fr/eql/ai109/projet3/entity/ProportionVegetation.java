package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="proportion_vegetation")
public class ProportionVegetation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProportionVegetationPK id;

	@Column(name="proportion_vege")
	private int proportionVege;
	
	@ManyToOne
	@JoinColumn(name="id_terrain", nullable=false, insertable=false, updatable=false)
	private Terrain terrain;
	
	@ManyToOne
	@JoinColumn(name="id_vegetation", nullable=false, insertable=false, updatable=false)
	private VegetationRef vegetationRef;

	public ProportionVegetationPK getId() {
		return id;
	}

	public void setId(ProportionVegetationPK id) {
		this.id = id;
	}

	public int getProportionVege() {
		return proportionVege;
	}

	public void setProportionVege(int proportionVege) {
		this.proportionVege = proportionVege;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public VegetationRef getVegetationRef() {
		return vegetationRef;
	}

	public void setVegetationRef(VegetationRef vegetationRef) {
		this.vegetationRef = vegetationRef;
	}

}
