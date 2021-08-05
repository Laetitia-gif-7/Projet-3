package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="motif_retrait_terrain")
public class MotifRetraitTerrain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_motif_retrait_terrain", unique=true, nullable=false)
	private int idMotifRetraitTerrain;
	
	@Column(name = "libelle_motif_retrait_terrain", length=254)
	private String libelleMotifRetraitTerrain;
	
	@OneToMany(mappedBy="motifRetraitTerrain")
	private List<Terrain> terrains;

	public int getIdMotifRetraitTerrain() {
		return idMotifRetraitTerrain;
	}

	public void setIdMotifRetraitTerrain(int idMotifRetraitTerrain) {
		this.idMotifRetraitTerrain = idMotifRetraitTerrain;
	}

	public String getLibelleMotifRetraitTerrain() {
		return libelleMotifRetraitTerrain;
	}

	public void setLibelleMotifRetraitTerrain(String libelleMotifRetraitTerrain) {
		this.libelleMotifRetraitTerrain = libelleMotifRetraitTerrain;
	}

	public List<Terrain> getTerrains() {
		return terrains;
	}

	public void setTerrains(List<Terrain> terrains) {
		this.terrains = terrains;
	}

}
