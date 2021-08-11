package fr.eql.ai109.projet3.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import fr.eql.ai109.projet3.entity.QuantiteEquipement;

public class ParametresReservationPrestation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private double cout;
	private int nbAnimaux;
	private int nbAnimauxRecommande;
	private Date dateDebut;
	private Date dateFin;
	//
	private List<QuantiteEquipement> equipementSupplementaire; // a payer
	private List<QuantiteEquipement> equipementSurTerrain;  // fixe
	// fast access
	private int longueurCloture;
	// computed values, nice to show in view
	private double qualiteTonte; // around 1.
	private double bienEtreAnimal; // around 1.
	private double ugbMoyen;
	// 
	private int nbTotalAnimauxTroupeau; // fixe, depend du troupeau
	private int nbAnimauxTroupeauLibres; //variable, ceux qui ne sont pas en prestation
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
	public int getNbAnimauxRecommande() {
		return nbAnimauxRecommande;
	}
	public void setNbAnimauxRecommande(int nbAnimauxRecommande) {
		this.nbAnimauxRecommande = nbAnimauxRecommande;
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
	public int getNbAnimauxTroupeauLibres() {
		return nbAnimauxTroupeauLibres;
	}
	public void setNbAnimauxTroupeauLibres(int nbAnimauxTroupeauLibres) {
		this.nbAnimauxTroupeauLibres = nbAnimauxTroupeauLibres;
	}
	
	public double getUgbMoyen() {
		return ugbMoyen;
	}
	public void setUgbMoyen(double ugbMoyen) {
		this.ugbMoyen = ugbMoyen;
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
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
}
