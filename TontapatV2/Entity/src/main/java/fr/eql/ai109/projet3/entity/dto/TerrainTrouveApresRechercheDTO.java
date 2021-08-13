package fr.eql.ai109.projet3.entity.dto;

import java.io.Serializable;
import java.util.Date;

import fr.eql.ai109.projet3.entity.Terrain;

public class TerrainTrouveApresRechercheDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Terrain terrain;
	private Date dateMin;
	
	private Date dateMax;
	private float pourcentagePropoMorpho;
	private float pourcentagePropoVege;
	private float pourcentageTotal;
	
	// pourcentage match sera calculé dans business
	// proportion morpho and proportion vegetation extrait de la bd
	
	public Terrain getTerrain() {
		return terrain;
	}
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	public float getPourcentageTotal() {
		return pourcentageTotal;
	}
	public void setPourcentageTotal(float pourcentageTotal) {
		this.pourcentageTotal = pourcentageTotal;
	}
	public float getPourcentagePropoVege() {
		return pourcentagePropoVege;
	}
	public void setPourcentagePropoVege(float pourcentagePropoVege) {
		this.pourcentagePropoVege = pourcentagePropoVege;
	}
	public float getPourcentagePropoMorpho() {
		return pourcentagePropoMorpho;
	}
	public void setPourcentagePropoMorpho(float pourcentagePropoMorpho) {
		this.pourcentagePropoMorpho = pourcentagePropoMorpho;
	}
	public Date getDateMin() {
		return dateMin;
	}
	public void setDateMin(Date dateMin) {
		this.dateMin = dateMin;
	}
	public Date getDateMax() {
		return dateMax;
	}
	public void setDateMax(Date dateMax) {
		this.dateMax = dateMax;
	}
	
}
