package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/* Extends the prestation entity :
 * - State design pattern to deal with all different steps in a prestation 
 * - add convenient link (Eleveur, Client..). but could be given by getFunction also ?
 */
public class PrestationBU implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private StatePrestation state;
	// owns an entity prestation
	private Prestation prestation;
	// extends entity attributes to ease treatment ? 
	private Utilisateur client;
	private Utilisateur eleveur;
	private Utilisateur berger;
	// private Utilisateur partenaire
	
	//default, should be overwritten by each state
	private String stateString = "UNIMPLEMENTED";
	private String templateXhtml = "error.xhtml";
	
	public String getTemplateXhtml() {
		return templateXhtml;
	}

	public void setTemplateXhtml(String templateXhtml) {
		this.templateXhtml = templateXhtml;
	}

	public PrestationBU() {
		System.out.println("Entry prestationBu constructor");
	}
	
	public PrestationBU(Prestation prest) {
		this.prestation = prest;
		// to deal with the setting of the State at construction
		// done by factory at the moment
	}
	
	// test, maybe not the best place (mix view and business)
	public String viewXHTML() {
		return "reserveParClient.xhtml";
	}
	
	public Utilisateur getClient() {
		return client;
	}

	public void setClient(Utilisateur client) {
		this.client = client;
	}

	public Utilisateur getEleveur() {
		return eleveur;
	}

	public void setEleveur(Utilisateur eleveur) {
		this.eleveur = eleveur;
	}
	
	// give direct access
	public Prestation getPrestation() {
		return prestation;
	}
	
	public void setPrestation(Prestation prestation) {
		this.prestation = prestation;
	}

	public String getStateString() {
		return stateString;
	}
	
	public void setStateString(String str) {
		this.stateString = str;
	}
	
	// to implement  in each state
	public void valide() {
		state.valide(this);
	}
	/*
	public void valideAvecDate(LocalDateTime date) {
		state.valideAvecDate(this);
	}*/

	public void annule() {
		state.annule(this);
	}
	
	public StatePrestation getState() {
		return state;
	}

	public void setState(StatePrestation state) {
		this.state = state;
		this.state.setStateName(this);
		this.state.setTemplateString(this);
	}
}
