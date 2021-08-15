package fr.eql.ai109.projet3.entity.dto;

import java.io.Serializable;

import fr.eql.ai109.projet3.entity.Prestation;

public class PrestationTrouveApresRechercheDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Prestation prestation;

	public Prestation getPrestation() {
		return prestation;
	}

	public void setPrestation(Prestation prestation) {
		this.prestation = prestation;
	}

}
