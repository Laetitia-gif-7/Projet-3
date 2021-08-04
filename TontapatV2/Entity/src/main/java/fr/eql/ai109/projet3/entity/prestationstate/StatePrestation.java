package fr.eql.ai109.projet3.entity.prestationstate;

import java.io.Serializable;

import fr.eql.ai109.projet3.entity.PrestationExt;

public interface StatePrestation extends Serializable {
	
	void valide(PrestationExt p);
	void annule(PrestationExt p);
	void testPrint(PrestationExt p);
	
}
