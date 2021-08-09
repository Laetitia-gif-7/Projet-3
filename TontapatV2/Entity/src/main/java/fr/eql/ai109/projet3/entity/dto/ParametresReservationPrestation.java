package fr.eql.ai109.projet3.entity.dto;

import java.io.Serializable;
import java.util.List;

import fr.eql.ai109.projet3.entity.QuantiteEquipement;

public class ParametresReservationPrestation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private float cout;
	private int nbAnimaux;
	private List<QuantiteEquipement> equipements;
	
	// computed values
	private float qualiteTonte; // around 1.
	private float bienEtreAnimal; // surface/animal ou around 1.
	
	// intermediate variables, usefull for view
	private int nbTotalAnimauxTroupeau;
	
	public float getCout() {
		return cout;
	}
	public void setCout(float cout) {
		this.cout = cout;
	}
	public int getNbAnimaux() {
		return nbAnimaux;
	}
	public void setNbAnimaux(int nbAnimaux) {
		this.nbAnimaux = nbAnimaux;
	}
	public List<QuantiteEquipement> getEquipements() {
		return equipements;
	}
	public void setEquipements(List<QuantiteEquipement> equipements) {
		this.equipements = equipements;
	}
	public float getQualiteTonte() {
		return qualiteTonte;
	}
	public void setQualiteTonte(float qualiteTonte) {
		this.qualiteTonte = qualiteTonte;
	}
	public float getBienEtreAnimal() {
		return bienEtreAnimal;
	}
	public void setBienEtreAnimal(float bienEtreAnimal) {
		this.bienEtreAnimal = bienEtreAnimal;
	}
	
	public int getNbTotalAnimauxTroupeau() {
		return nbTotalAnimauxTroupeau;
	}
	public void setNbTotalAnimauxTroupeau(int nbTotalAnimauxTroupeau) {
		this.nbTotalAnimauxTroupeau = nbTotalAnimauxTroupeau;
	}
	
	
}
