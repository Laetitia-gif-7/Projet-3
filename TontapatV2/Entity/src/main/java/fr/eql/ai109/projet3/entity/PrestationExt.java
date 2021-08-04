package fr.eql.ai109.projet3.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import fr.eql.ai109.projet3.entity.prestationstate.ReserveParClient;
import fr.eql.ai109.projet3.entity.prestationstate.StatePrestation;

/* Extends the prestation entity :
 * - State design pattern to deal with all different steps in a prestation 
 * - add convenient link (TerrainId)
 */
public class PrestationExt implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private StatePrestation state;
	
	// owns an entity prestation
	private Prestation prestation;
	// extends entity attributes to ease treatment ? 
	// or include in prestation  ?
	//private Utilisateur client;
	//private Utilisateur eleveur;
	//private Utilisateur berger;
	
	private String testString = "UNIMPLEMENTED";
	
	public PrestationExt() {
		System.out.println("Entry prestationExt constructor");
	}
	
	public PrestationExt(Prestation prest) {
		this.prestation = prest;
		// to deal with the setting of the State at construction
		this.state = ReserveParClient.RESERVEPARCLIENT;
	}
	
	// give direct access
	public Prestation getPrestation() {
		return prestation;
	}
	
	public void setPrestation(Prestation prestation) {
		this.prestation = prestation;
	}
	
	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}

	// or include getters and setters of prestation ?
	public LocalDateTime getDebutPrestation() {
		return prestation.getDebutPrestation();
	}
	
	// to implement  in each state
	public void valide() {
		state.valide(this);
	}

	public void annule() {
		state.annule(this);
	}
	
	public void testPrint() {
		state.testPrint(this);
	}
	
	public StatePrestation getState() {
		return state;
	}

	public void setState(StatePrestation state) {
		this.state = state;
	}
	
}
