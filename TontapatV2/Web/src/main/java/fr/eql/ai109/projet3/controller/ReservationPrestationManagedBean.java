package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;
import fr.eql.ai109.projet3.ibusiness.ReservationPrestationIBusiness;
import fr.eql.ai109.projet3.ibusiness.TerrainIBusiness;
import fr.eql.ai109.projet3.ibusiness.TroupeauIBuisness;

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
	
	@EJB
	private TroupeauIBuisness troupeauIBusiness;
	
	// pour avoir access de xhtml
	private Terrain terrain; // peut contenir du matériel
	private int idTerrain;
	private Troupeau troupeau;
	private int idTroupeau = 1;
	
	// variable from the formulaire of reservation
	
	//private int nbAnimaux;
	private Date dateDebutLimit, dateFinLimit;
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
	//QuantiteEquipementPrestation equipementConseille; 
	
	@PostConstruct
	void init() {
		System.out.println("init ReservationPrestationMB");
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String,String> params = ctx.getExternalContext().getRequestParameterMap();
		// idTerrain, idTroupeau, date debut et fin to extract from the url
		String idTerrainString = params.get("idTerrain");
		idTerrain =Integer.parseInt(idTerrainString);
		String idTroupeauString = params.get("idTroupeau");
		idTerrain =Integer.parseInt(idTroupeauString);
		
		terrain = terrainIBusiness.findTerrainByIdTerrainAndUtilisateur(idTerrain, utilisateurConnecte);
		//troupeau = troupeauIBusiness.findTroupeauxByUtilisateur(utilisateurConnecte)
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		try {
			dateDebutLimit = sdf.parse("01-8-2021");
			dateFinLimit = sdf.parse("01-9-2021");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// retour attendu :
		// - prix
		// - nbAnimaux
		// - longueurCloture, abri, abreuvoir (QuantiteEquipementPrestation)
		// - qualiteTonte
		// - bienEtreAnimal
		prp = resaPrestaIBusiness.calculeDefautPrestation(idTerrain, idTroupeau, dateDebutLimit, dateFinLimit);
		//prp.setLongueurCloture(
		//	prp.getLongueurClotureSu());
		System.out.println("toto");
		
	}
	
	/* Quand on bouge les curseurs, appel ajax from xhtml
	 - prix
	 - abri, abreuvoir
	 - qualitetonte, bienEtreAnimal
	*/	                                                            // ou prp 
	public void actualisePrixPrestation(int idTerrain, int idTroupeau, 
			Date dateDebut, Date dateFin, int nbAnimaux, int longueurCloture) {
		// prp = resaPrestaIBusiness.actualise(....)
		//return null;
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("destroy ReservationPrestationMB");
	}
	
	public Troupeau getTroupeau() {
		return troupeau;
	}

	public void setTroupeau(Troupeau troupeau) {
		this.troupeau = troupeau;
	}
	
	public Date getDateDebutLimit() {
		return dateDebutLimit;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebutLimit = dateDebut;
	}

	public Date getDateFin() {
		return dateFinLimit;
	}

	public void setDateFin(Date dateFin) {
		this.dateFinLimit = dateFin;
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
