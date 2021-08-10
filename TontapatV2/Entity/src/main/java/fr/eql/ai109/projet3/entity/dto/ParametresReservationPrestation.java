package fr.eql.ai109.projet3.entity.dto;

import java.io.Serializable;
import java.util.List;

import fr.eql.ai109.projet3.entity.QuantiteEquipement;

public class ParametresReservationPrestation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private double cout;
	private int nbAnimaux;
	private List<QuantiteEquipement> equipements;
	
	// computed values, nice to show in view
	private double qualiteTonte; // around 1.
	private double bienEtreAnimal; // surface/animal ou around 1.
	private double ugbMoyen;
	
	// intermediate variables, usefull for view ?
	private int nbTotalAnimauxTroupeau;
	/*
	private double prixTransport;
	private double prixMateriel;
	private double prixAnimaux;
	*/
	
	public double getCout() {
		return cout;
	}
	public void setCout(double cout) {
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
	public double getQualiteTonte() {
		return qualiteTonte;
	}
	public void setQualiteTonte(float qualiteTonte) {
		this.qualiteTonte = qualiteTonte;
	}
	public double getBienEtreAnimal() {
		return bienEtreAnimal;
	}
	public void setQualiteTonte(double qualiteTonte) {
		this.qualiteTonte = qualiteTonte;
	}
	public void setBienEtreAnimal(double bienEtreAnimal) {
		this.bienEtreAnimal = bienEtreAnimal;
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
	
	public double getUgbMoyen() {
		return ugbMoyen;
	}
	public void setUgbMoyen(double ugbMoyen) {
		this.ugbMoyen = ugbMoyen;
	}
}
