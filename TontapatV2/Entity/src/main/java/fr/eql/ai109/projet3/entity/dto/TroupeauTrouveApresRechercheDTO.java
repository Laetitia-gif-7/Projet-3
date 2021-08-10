package fr.eql.ai109.projet3.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import fr.eql.ai109.projet3.entity.Troupeau;

public class TroupeauTrouveApresRechercheDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Troupeau troupeau;
	private Date dateMin;
	private Date dateMax;
	private float pourcentagePropoMorpho;
	private float pourcentagePropoVege;
	
	// pourcentage match sera calculé dans business
	// proportion morpho and proportion vegetation extrait de la bd
	
	
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
	public Troupeau getTroupeau() {
		return troupeau;
	}
	public void setTroupeau(Troupeau troupeau) {
		this.troupeau = troupeau;
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
