package fr.eql.ai109.projet3.entity.dto;

import java.io.Serializable;
import java.util.List;

import fr.eql.ai109.projet3.entity.QuantiteEquipement;

public class ParametresReservationPrestation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private double cout;
	private int nbAnimaux;
	private int nbAnimauxRecommande;
	// init at list<>(), easier ?
	private List<QuantiteEquipement> equipementSupplementaire;
	private List<QuantiteEquipement> equipementSurTerrain;
	private int longueurCloture;
	
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
	public List<QuantiteEquipement> getEquipementSupplementaire() {
		return equipementSupplementaire;
	}
	
	// here should update longueurCloture
	public void setEquipementSupplementaire(List<QuantiteEquipement> equipementSupplementaire) {
		this.equipementSupplementaire = equipementSupplementaire;
	}
	public List<QuantiteEquipement> getEquipementSurTerrain() {
		return equipementSurTerrain;
	}
	public void setEquipementSurTerrain(List<QuantiteEquipement> equipementSurTerrain) {
		this.equipementSurTerrain = equipementSurTerrain;
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
	
	public double getQualiteTonte() {
		return qualiteTonte;
	}
	
	public void setQualiteTonte(double qualiteTonte) {
		this.qualiteTonte = qualiteTonte;
	}
	
	public double getBienEtreAnimal() {
		return bienEtreAnimal;
	}
	
	public void setBienEtreAnimal(double bienEtreAnimal) {
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
	
	public int getNbAnimauxRecommande() {
		return nbAnimauxRecommande;
	}
	
	public void setNbAnimauxRecommande(int nbAnimauxRecommande) {
		this.nbAnimauxRecommande = nbAnimauxRecommande;
	}
	
	public int getLongueurClotureSupplementaire() {
		for( QuantiteEquipement qe : equipementSupplementaire )
			if( qe.getEquipement().getLibelleEquipement() == "clôture" )
				return qe.getQuantite();
		return 0;
	}
	/*
	public void setLongueurClotureSupplementaire(int longueur) {
		System.out.println("entry");
		for( QuantiteEquipement qe : equipementSupplementaire )
			if( qe.getEquipement().getLibelleEquipement() == "clôture" )
				qe.setQuantite(longueur);
	}
	*/
	public int getLongueurCloture() {
		return longueurCloture;
	}
	public void setLongueurCloture(int longueurCloture) {
		this.longueurCloture = longueurCloture;
	}
}
