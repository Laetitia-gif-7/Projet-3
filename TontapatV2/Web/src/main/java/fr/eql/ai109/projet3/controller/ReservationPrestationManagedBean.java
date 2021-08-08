package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
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
	TerrainIBusiness terrainIBusiness;
	
	// @EJB
	// TroupeauIBusiness troupeauIBusiness;
	
	// needs to fill a reservation :
	// include materiel disponible sur le terrain
	private Terrain terrain;
	
	private int idTerrain = 1;
	// include 
	private Troupeau troupeau;
	private int idTroupeau = 1;
	
	// variables from the formulaire of reservation
	float cout;
	// Some may be includued 
	// QuantiteEquipement, CompositionTroupeau entity  
	int nbAnimaux;
	int longueurCloture;
	
	// Fixé dans la vue, le client ne peut pas modifier ces paramètres
	int nbAbreuvoir;
	int nbAbri;
	QuantiteEquipementPrestation equipementPayant;
	QuantiteEquipementPrestation equipementConseille;
	
	@PostConstruct
	void init() {
		System.out.println("init ReservationPrestationBusiness");
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String,String> params = ctx.getExternalContext().getRequestParameterMap();
		String idTerrainString = params.get("id");
		System.out.println("id terrain en entier "+ idTerrainString);
		idTerrain =Integer.parseInt(idTerrainString);
		System.out.println("id terrain en entier "+ idTerrain);
		
		//Map<String, QuantiteEquipementPrestation> resaPrestaIBusiness.
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("destroy ReservationPrestationIBusiness");
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
