package fr.eql.ai109.projet3.entity.prestationstate;

import java.io.Serializable;

import fr.eql.ai109.projet3.entity.PrestationBU;

public interface StatePrestation extends Serializable {
	
	void valide(PrestationBU p);
	void annule(PrestationBU p);
	void setStateName(PrestationBU p);
	
}
