package fr.eql.ai109.projet3.controller;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.google.j2objc.annotations.ReflectionSupport.Level;

import fr.eql.ai109.projet3.entity.Incident;
import fr.eql.ai109.projet3.entity.IncidentRef;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.PrestationBU;
import fr.eql.ai109.projet3.entity.Tache;
import fr.eql.ai109.projet3.entity.TacheRef;
import fr.eql.ai109.projet3.entity.Terrain;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.entity.dto.ParametresTache;
import fr.eql.ai109.projet3.ibusiness.IncidentIBusiness;
import fr.eql.ai109.projet3.ibusiness.PrestationIBusiness;
import fr.eql.ai109.projet3.ibusiness.TacheIBusiness;

// @RequestScoped

@ManagedBean(name = "mbPresta")
@ViewScoped
public class PrestationManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{mbCompte.utilisateur}")
	private Utilisateur utilisateurConnecte;
//	private List<Prestation> prestationx;
	private List<IncidentRef> incidentRef;
	private List<SelectItem> listSelectIncident;
	private List<TacheRef> tacheRefs;
	private List<SelectItem> listSelectTaches;
	private List<Incident> incidents;
	private IncidentRef incidentRefSelectionne;
	private TacheRef tacheRefSelectionne;
	private int testId;
	private ParametresTache pt;
	private Tache tache;
	


	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public IncidentRef getIncidentRefSelectionne() {
		return incidentRefSelectionne;
	}

	public void setIncidentRefSelectionne(IncidentRef incidentRefSelectionne) {
		this.incidentRefSelectionne = incidentRefSelectionne;
	}

	private Map<Integer,PrestationBU> prestations;
	
	private String viewXHTML = "reserveParClient.xhtml";
	
	public String getViewXHTML() {
		return viewXHTML;
	}

	public void setViewXHTML(String viewXHTML) {
		this.viewXHTML = viewXHTML;
	}

	@EJB
	private PrestationIBusiness prestaIBusiness;
	@EJB
	private IncidentIBusiness incidentIBusiness;
	
	@EJB
	private TacheIBusiness tacheIBusiness;
	
	
	
	@PostConstruct
	public void init() {
		prestations = prestaIBusiness.findPrestationsByUtilisateur(utilisateurConnecte);
		//List list = prestaIBusiness.findPrestationsByUtilisateur(utilisateurConnecte);
		System.out.println("done");
		for (Integer prestationKey : prestations.keySet()) {
			System.out.println("prestExt :" + prestations.get(prestationKey).getPrestation().getDebutPrestation());
			System.out.println("state string :"+ prestations.get(prestationKey).getStateString());
		}
		incidentRef = incidentIBusiness.findAllIncidentRef();
		
		tacheRefs = tacheIBusiness.findAllTacheRefs();
		
//		listSelectIncident = new ArrayList<SelectItem>();
//		for (IncidentRef incidentRef2 : incidentRef) {
//			listSelectIncident.add(new SelectItem(incidentRef2.getIdIncidentRef(),incidentRef2.getLibelleIncident()));
//		}
		//incidentRefSelectionne = incidentRef.get(0);
		pt = new ParametresTache();

	}
	
	public List<SelectItem> getListSelectIncident() {
		List<SelectItem> listSelectIncident = new ArrayList<SelectItem>();
		for (IncidentRef incidentRef2 : incidentRef) {
			listSelectIncident.add(new SelectItem(incidentRef2.getIdIncidentRef(), incidentRef2.getLibelleIncident()));
		}
		return listSelectIncident;
	}
	
	public List<SelectItem> getListSelectTaches() {
		List<SelectItem> listSelectTaches = new ArrayList<SelectItem>();
		for (TacheRef tacheRef : tacheRefs) {
			listSelectTaches.add(new SelectItem(tacheRef.getIdTacheRef(), tacheRef.getLibelleTache()));
		}
		return listSelectTaches;
	}
	
//	public void incidentRefSelectionne() {
////		incidentRef.set(idIncidentRefSelectionne, incidentRefSelectionne)
////		
////        utilisateur.setPrenom(prenom);
////        utilisateurDao.creer( utilisateur );
//		String idIncidentRefSelectionne = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("formulaire:idIncidentRefSelectionne");
//        FacesMessage message = new FacesMessage( "Incident enregitré !" );
//        FacesContext.getCurrentInstance().addMessage( null, message );
//    }
	
	public void enregistrerUnIncident(Prestation prestation, IncidentRef incidentRef) {
		incidentIBusiness.DeclarationIncident(prestation, utilisateurConnecte, incidentRef);
	}
	
	public void AjoutTache(Prestation prestation, TacheRef tacheRef) {
		Date debutPlanifiee = pt.getDebutPlannifiee();
		Date finPlanifiee = pt.getFinPlanifiee();
		
		tache = tacheIBusiness.AjouterTache(prestation, utilisateurConnecte, tacheRef, debutPlanifiee, finPlanifiee);
	}
	
	public void ValideTache() {
		int idTache = tache.getIdTache();
		Date debutRealisation = pt.getDebutRealisation();
		Date finRealisation = pt.getFinRealisation();
		String rapport = pt.getRapport();
		
		tache = tacheIBusiness.ValiderTache(idTache, utilisateurConnecte, debutRealisation, finRealisation, rapport);
	}
	
	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}
	
	public String validerEtatDesLieux(int idPrestation) {
		prestaIBusiness.valideEtatDesLieux(idPrestation, utilisateurConnecte);
		return "prestations.xhtml";
	}
	
	public String signerContrat(int idPrestation) {
		prestaIBusiness.valideContrat(idPrestation, utilisateurConnecte);
		return "prestations.xhtml";
	}

	public void setListSelectIncident(List<SelectItem> listSelectIncident) {
		this.listSelectIncident = listSelectIncident;
	}
	
	public void setListSelectTaches(List<SelectItem> listSelectTaches) {
		this.listSelectTaches = listSelectTaches;
	}

	@PreDestroy
	void destroy() {
		System.out.println("destroy PrestationDao");
	}
	
	
	// should make action on a specific prestation
	public void valide(int idPrestation) {
		System.out.println("Valider : " + idPrestation);
//      no need with HahMap
//		PrestationBU pbu = prestations.stream()
//				.filter(pr -> pr.getPrestation().getIdPrestation() == idPrestation).findFirst().orElse(null);
		PrestationBU newPbu = prestaIBusiness.valide(prestations.get(idPrestation));
		// to insert in prestations
		System.out.println("state : " + newPbu.getStateString());
		System.out.println("IdPrestation : "+ idPrestation + ", newPbu Id : " + newPbu.getPrestation().getIdPrestation());
		
		// assert in debug mode
		assert idPrestation == newPbu.getPrestation().getIdPrestation();
		// update the value
		prestations.put(newPbu.getPrestation().getIdPrestation(), newPbu);
	}
	
	public void valideAvecDate(int idPrestation, LocalDateTime date) {
		System.out.println("Valider avec date : " + idPrestation);
		//PrestationBU newPbu = prestaIBusiness.valideAvecDate(prestations.get(idPrestation), date);
	}
	
	public void annule(int idPrestation) {
		System.out.println("Annuler la prestation :" + idPrestation);
	}

	public String formatMyLocalDateTime(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return date.format(formatter);
	}
	
	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
		this.utilisateurConnecte = utilisateurConnecte;
	}

	public Map<Integer,PrestationBU> getPrestations() {
		return prestations;
	}

	public void setPrestations(Map<Integer,PrestationBU> prestations) {
		this.prestations = prestations;
	}
	
	public String declarerIncident(int idPrestation) {
		return "incidents.xhtml?faces-redirect=false&id=" + Integer.toString(idPrestation);
	}
	

	public List<IncidentRef> getIncidentRef() {
		return incidentRef;
	}

	public void setIncidentRef(List<IncidentRef> incidentRef) {
		this.incidentRef = incidentRef;
	}

	public List<TacheRef> getTacheRefs() {
		return tacheRefs;
	}

	public void setTacheRefs(List<TacheRef> tacheRefs) {
		this.tacheRefs = tacheRefs;
	}

	public TacheRef getTacheRefSelectionne() {
		return tacheRefSelectionne;
	}

	public void setTacheRefSelectionne(TacheRef tacheRefSelectionne) {
		this.tacheRefSelectionne = tacheRefSelectionne;
	}
	

	public ParametresTache getPt() {
		return pt;
	}

	public void setPt(ParametresTache pt) {
		this.pt = pt;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

}
