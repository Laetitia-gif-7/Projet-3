package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.eql.ai109.projet3.entity.QuantiteEquipementPrestation;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;
import fr.eql.ai109.projet3.ibusiness.ReservationPrestationIBusiness;
import fr.eql.ai109.projet3.ibusiness.TerrainIBusiness;

/*
 Request Scope should be enought, with only ajax calls ?  
*/

@ManagedBean(name="mbReservation")
@RequestScoped
public class ReservationPrestationManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mbCompte.utilisateur}")
	private Utilisateur utilisateurConnecte;
	
	@EJB
	private ReservationPrestationIBusiness resaPrestaIBusiness;
	
	@EJB
	private TerrainIBusiness terrainIBusiness;
	
	// @EJB
	// TroupeauIBusiness troupeauIBusiness;
	
	// pour avoir access de xhtml
	private Terrain terrain; // peut contenir du matériel
	private int idTerrain;
	private Troupeau troupeau;
	private int idTroupeau = 1;
	
	// variable from the formulaire of reservation
	
	//private int nbAnimaux;
	private Date dateDebut, dateFin;
	/*
	private int longueurCloture;
	*/
	// Sera fixé dans la vue, le client ne peut pas modifier ces paramètres
	/*
	private float cout; 
	private int nbAbreuvoir;
	private int nbAbri;
	*/
	
	private ParametresReservationPrestation prp;
	
	// equipement à commander au prestataire extérieur
	//QuantiteEquipementPrestation equipementPayant;
	// 
	//QuantiteEquipementPrestation equipementConseille; 
	
	

	@PostConstruct
	void init() {
		System.out.println("init ReservationPrestationMB");
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String,String> params = ctx.getExternalContext().getRequestParameterMap();
		// idTerrain, idTroupeau, date debut et fin
		String idTerrainString = params.get("id");
		idTerrain =Integer.parseInt(idTerrainString);
		
		
		
		System.out.println("id terrain en entier "+ idTerrainString);
		System.out.println("id terrain en entier "+ idTerrain);
		
		dateDebut = new Date(2021,6,01);
		dateFin = new Date(2021,9,01);
		
		// retour attendu :
		// - prix
		// - nbAnimaux
		// - longueurCloture, abri, abreuvoir (QuantiteEquipementPrestation)
		// - qualiteTonte
		// - bienEtreAnimal
		prp = resaPrestaIBusiness.calculeDefautPrestation(idTerrain, idTroupeau, dateDebut, dateFin);
	}
	
	/* Quand on bouge les curseurs, appel ajax from xhtml
	 - prix
	 - abri, abreuvoir
	 - qualitetonte, bienEtreAnimal
	*/
	public ParametresReservationPrestation actualisePrixPrestation(int idTerrain, int idTroupeau, 
			Date dateDebut, Date dateFin, int nbAnimaux, int longueurCloture) {
		//resaPrestaIBusiness.
		return null;
		
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("destroy ReservationPrestationIBusiness");
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

	public ParametresReservationPrestation getPrp() {
		return prp;
	}

	public void setPrp(ParametresReservationPrestation prp) {
		this.prp = prp;
	}
	
	public int getIdTerrain() {
		return idTerrain;
	}

	public void setIdTerrain(int idTerrain) {
		this.idTerrain = idTerrain;
	}

	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
		this.utilisateurConnecte = utilisateurConnecte;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public int getIdTroupeau() {
		return idTroupeau;
	}

	public void setIdTroupeau(int idTroupeau) {
		this.idTroupeau = idTroupeau;
	}
	
}
