package fr.eql.ai109.projet3.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="proportion_morphologie")
public class ProportionMorphologie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProportionMorphologiePK id;
	
	@Column(name="proportion_morphologie")
	private int proportionMorphologie;
	
	@ManyToOne
	@JoinColumn(name="id_morphologie", nullable=false, insertable=false, updatable=false)
	private MorphologieRef morphologieRef;
	
	@ManyToOne
	@JoinColumn(name="id_terrain", nullable=false, insertable=false, updatable=false)
	private Terrain terrain;

	public ProportionMorphologiePK getId() {
		return id;
	}

	public void setId(ProportionMorphologiePK id) {
		this.id = id;
	}

	public int getProportionMorphologie() {
		return proportionMorphologie;
	}

	public void setProportionMorphologie(int proportionMorphologie) {
		this.proportionMorphologie = proportionMorphologie;
	}

	public MorphologieRef getMorphologieRef() {
		return morphologieRef;
	}

	public void setMorphologieRef(MorphologieRef morphologieRef) {
		this.morphologieRef = morphologieRef;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

}
