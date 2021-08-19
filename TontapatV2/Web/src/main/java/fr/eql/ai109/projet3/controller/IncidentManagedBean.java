package fr.eql.ai109.projet3.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import fr.eql.ai109.projet3.entity.Incident;
import fr.eql.ai109.projet3.entity.IncidentRef;
import fr.eql.ai109.projet3.entity.Prestation;
import fr.eql.ai109.projet3.entity.Utilisateur;
import fr.eql.ai109.projet3.ibusiness.IncidentIBusiness;

@ManagedBean(name = "mbIncident")
@ViewScoped
public class IncidentManagedBean implements Serializable{
	
	
		private static final long serialVersionUID = 1L;
		
		@ManagedProperty(value = "#{mbCompte.utilisateur}")
		private Utilisateur utilisateurConnecte;
		private Prestation prestation;
		private List<IncidentRef> incidentRef;
		private List<SelectItem> listSelectIncident;
		private List<Incident> incidents;
		private IncidentRef incidentRefSelectionne;
		
		private int idPrestation;
		
		@EJB
		private IncidentIBusiness incidentIBusiness;
		
		
		@PostConstruct
		public void init() {
			incidentRef = incidentIBusiness.findAllIncidentRef();
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String,String> params = ctx.getExternalContext().getRequestParameterMap();
			// idTerrain, idTroupeau, date debut et fin to extract from the url
			String idPrestationString = params.get("id");
			idPrestation =Integer.parseInt(idPrestationString);
//			listSelectIncident = new ArrayList<SelectItem>();
//			for (IncidentRef incidentRef2 : incidentRef) {
//				listSelectIncident.add(new SelectItem(incidentRef2.getIdIncidentRef(),incidentRef2.getLibelleIncident()));
//			}
			//incidentRefSelectionne = incidentRef.get(0);
		}
		
		public List<SelectItem> getListSelectIncident() {
			List<SelectItem> listSelectIncident = new ArrayList<SelectItem>();
			for (IncidentRef incidentRef2 : incidentRef) {
				listSelectIncident.add(new SelectItem(incidentRef2.getIdIncidentRef(), incidentRef2.getLibelleIncident()));
			}
			return listSelectIncident;
		}
		
//		public void incidentRefSelectionne() {
////			incidentRef.set(idIncidentRefSelectionne, incidentRefSelectionne)
////			
////	        utilisateur.setPrenom(prenom);
////	        utilisateurDao.creer( utilisateur );
//			String idIncidentRefSelectionne = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("formulaire:idIncidentRefSelectionne");
//	        FacesMessage message = new FacesMessage( "Incident enregitré !" );
//	        FacesContext.getCurrentInstance().addMessage( null, message );
//	    }
		
		public String enregistrerUnIncident(IncidentRef incidentRefSelectionne) {
			incidentIBusiness.DeclarationIncident(idPrestation, utilisateurConnecte, incidentRefSelectionne);
			return "prestations.xhtml";
			 
			
		}
		
		public List<Incident> getIncidents() {
			return incidents;
		}

		public void setIncidents(List<Incident> incidents) {
			this.incidents = incidents;
		}

		public void setListSelectIncident(List<SelectItem> listSelectIncident) {
			this.listSelectIncident = listSelectIncident;
		}

		@PreDestroy
		void destroy() {
			System.out.println("destroy IncidentDao");
		}
		
		
		public Utilisateur getUtilisateurConnecte() {
			return utilisateurConnecte;
		}

		public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
			this.utilisateurConnecte = utilisateurConnecte;
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

		public Prestation getPrestation() {
			return prestation;
		}

		public void setPrestation(Prestation prestation) {
			this.prestation = prestation;
		}
		
		public IncidentRef getIncidentRefSelectionne() {
			return incidentRefSelectionne;
		}

		public void setIncidentRefSelectionne(IncidentRef incidentRefSelectionne) {
			this.incidentRefSelectionne = incidentRefSelectionne;
		}

		
	


}
