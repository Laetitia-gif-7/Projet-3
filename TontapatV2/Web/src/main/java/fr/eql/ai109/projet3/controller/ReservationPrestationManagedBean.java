package fr.eql.ai109.projet3.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SlideEndEvent;

import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Troupeau;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.entity.dto.ParametresReservationPrestation;
import fr.eql.ai109.projet3.ibusiness.PrestationIBusiness;
import fr.eql.ai109.projet3.ibusiness.ReservationPrestationIBusiness;
import fr.eql.ai109.projet3.ibusiness.TerrainIBusiness;
import fr.eql.ai109.projet3.ibusiness.TroupeauIBusiness;

@ManagedBean(name="mbReservation")
@ViewScoped
public class ReservationPrestationManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mbCompte.utilisateur}")
	private Utilisateur utilisateurConnecte;
	
	@EJB
	private ReservationPrestationIBusiness resaPrestaIBusiness;
	@EJB
	private TerrainIBusiness terrainIBusiness;
	@EJB
	private TroupeauIBusiness troupeauIBusiness;
	@EJB
	private PrestationIBusiness prestationIBusiness;
	
	// pour avoir access de xhtml
	private Terrain terrain; // peut contenir du matériel
	private int idTerrain;
	private Troupeau troupeau;
	private int idTroupeau = 1;
	
	// dates limites, provenant de l'algorithme de recherche et donné dans l'url
	private Date dateDebutLimit, dateFinLimit;
	// dto to go and back with business
	private ParametresReservationPrestation prp;
	
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
		String dateMinUrl = params.get("dateMin");
		String dateMaxUrl = params.get("dateMax");
		// idPrestation différent de null => je sais que la demande vient de l'éleveur
		//  => sinon nouvelle prestation à faire
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			dateDebutLimit = sdf.parse(dateMinUrl); 
			dateFinLimit = sdf.parse(dateMaxUrl);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// really needed ?
		terrain = terrainIBusiness.findTerrainByIdTerrainAndUtilisateur(idTerrain, utilisateurConnecte);
		// retour attendu :
		// - prix
		// - nbAnimaux
		// - longueurCloture, abri, abreuvoir (QuantiteEquipementPrestation)
		// - qualiteTonte
		// - bienEtreAnimal
		prp = resaPrestaIBusiness.calculeDefautPrestation(idTerrain, idTroupeau, dateDebutLimit, dateFinLimit);
	}
	
	/* Quand on bouge les curseurs et les dates, appel ajax from xhtml, on recalcule :
	 - prix
	 - nb abris, nb abreuvoirs
	 - qualitetonte, bienEtreAnimal */ 
	public void updateView() {
		System.out.println("entryUpdateView");
		System.out.println("value of prpr.nb aniamux :" + prp.getNbAnimaux());
		System.out.println("value of prpr.nb cloture :" + prp.getLongueurCloture());
		System.out.println("value of prpr.nb cloture supplementaitre :" + prp.getLongueurClotureSupplementaire());
		// if update cloture must be adjusted ??
		prp = resaPrestaIBusiness.actualisePrixPrestation(idTerrain, idTroupeau, prp);
	}
	
	public String validerPrestationParClient() {
		prestationIBusiness.createPrestationClient(utilisateurConnecte, prp, idTerrain, idTroupeau);
		return "prestations.xhtml";
	}
	// for tests with ajax, to move to another branch
	public void updateAjaxPrimefaces(SlideEndEvent event) {
		System.out.println("testUpdateAjaxPrimefaces event: "+ event);
		System.out.println("getvalue :" + event.getValue());
		System.out.println("nb aniamux :" + prp.getNbAnimaux());
		System.out.println("longueur cloture :" + prp.getLongueurCloture());
		prp = resaPrestaIBusiness.actualisePrixPrestation(idTerrain, idTroupeau, prp);
	}
	public void testUpdateAjax() {
		System.out.println("testUpdateAjax \n");
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("destroy ReservationPrestationMB really ??");
	}
	
	public Date getDateDebutLimit() {
		return dateDebutLimit;
	}
	public void setDateDebutLimit(Date dateDebutLimit) {
		this.dateDebutLimit = dateDebutLimit;
	}
	public Date getDateFinLimit() {
		return dateFinLimit;
	}
	public void setDateFinLimit(Date dateFinLimit) {
		this.dateFinLimit = dateFinLimit;
	}

	public Troupeau getTroupeau() {
		return troupeau;
	}
	public void setTroupeau(Troupeau troupeau) {
		this.troupeau = troupeau;
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
